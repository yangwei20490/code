package com.yw.springbootdemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssi.common.web.ResponseData;
import com.yw.springbootdemo.model.User;
import com.yw.springbootdemo.service.IUploadService;
import com.yw.springbootdemo.service.IUserService;
import com.yw.springbootdemo.service.impl.UserService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private UserService userServiceImpl;

    @GetMapping(value = "/test")
    public String test() {
        return "hello world";
    }

    @PostMapping(value = "/list")
    public ResponseData list(@RequestParam Map<String, String> params) {
        ResponseData responseData = ResponseData.createResponseData();
        Page page = new Page(Integer.valueOf(params.get("pageNum")), Integer.valueOf(params.get("pageSize")));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String name = params.get("name");
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        responseData.setData(userService.page(page, wrapper));
        responseData.setCode(ResponseData.CODE_SUCCESS);
        responseData.buildSuccessMsg(ResponseData.MSG_SUCCESS_QUERY_SUCCESS);
        return responseData;
    }

    @PostMapping(value = "/add")
    public ResponseData add(@RequestBody User user) {
        ResponseData responseData = ResponseData.createResponseData();
        try {
            userService.encryptPwd(user);
            userService.save(user);
            responseData.buildSuccessMsg(ResponseData.MSG_SUCCESS_ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.buildFailMsg(ResponseData.CODE_FAIL, "用户添加失败", "用户添加失败");
        }
        return responseData;
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseData getById(@PathVariable String id) {
       ResponseData responseData = ResponseData.createResponseData();
       responseData.setData(userService.getById(id));
       responseData.setCode(ResponseData.CODE_SUCCESS);
       responseData.buildSuccessMsg(ResponseData.MSG_SUCCESS_QUERY_SUCCESS);
       return responseData;
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseData deleteById(@PathVariable String id) {
        ResponseData responseData = ResponseData.createResponseData();
        userService.removeById(id);
        responseData.buildSuccessMsg(ResponseData.MSG_SUCCESS_DELETE_SUCCESS);
        return responseData;
    }

    @PutMapping(value = "/update")
    public ResponseData update(@RequestBody User user) {
        ResponseData responseData = ResponseData.createResponseData();
        userService.updateById(user);
        responseData.buildSuccessMsg(ResponseData.MSG_SUCCESS_UPDATE_SUCCESS);
        return responseData;
    }

    @PostMapping(value = "/upload")
    public ResponseData upload(@RequestParam("file") MultipartFile file) {
        ResponseData responseData = ResponseData.createResponseData();
        uploadService.importUsers(file);
        responseData.buildSuccessMsg("用户导入成功");
        return responseData;
    }

    @PutMapping(value = "/abbreviate")
    public ResponseData abbreviate() throws BadHanyuPinyinOutputFormatCombination {
        ResponseData responseData = ResponseData.createResponseData();
        userService.abbreviate();
        return responseData;
    }

    @GetMapping(value = "/export")
    public ResponseData export() {
        ResponseData responseData = ResponseData.createResponseData();
        userService.export();
        return responseData;
    }

    @GetMapping(value = "/testQueue")
    public String testQueue() {
        return "hello world";
    }

    @PostMapping(value = "/uploadByEasyexcel")
    public ResponseData uploadByEasyexcel(@RequestParam("file") MultipartFile file) throws IOException {
        ResponseData responseData = ResponseData.createResponseData();
        EasyExcel.read(file.getInputStream(), User.class, new DemoDataListener(userServiceImpl)).sheet().doRead();
//        uploadService.importUsers(file);
        responseData.buildSuccessMsg("用户导入成功");
        return responseData;
    }

    // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
    public class DemoDataListener extends AnalysisEventListener<User> {
        private final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 5;
        List<User> list = new ArrayList<User>();
        /**
         * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
         */
        private UserService demoDAO;
//        public DemoDataListener() {
//            // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//            demoDAO = new DemoDAO();
//        }
        /**
         * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
         *
         * @param demoDAO
         */
        public DemoDataListener(UserService demoDAO) {
            this.demoDAO = demoDAO;
        }
        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data
         *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(User data, AnalysisContext context) {
            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            list.add(data);
            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if (list.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                list.clear();
            }
        }
        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            saveData();
            LOGGER.info("所有数据解析完成！");
        }
        /**
         * 加上存储数据库
         */
        private void saveData() {
            LOGGER.info("{}条数据，开始存储数据库！", list.size());
            demoDAO.saveBatch(list);
            LOGGER.info("存储数据库成功！");
        }
    }

}
