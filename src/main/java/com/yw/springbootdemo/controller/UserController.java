package com.yw.springbootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssi.common.web.ResponseData;
import com.yw.springbootdemo.model.User;
import com.yw.springbootdemo.service.IUploadService;
import com.yw.springbootdemo.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadService uploadService;

    @GetMapping(value = "/test")
    public String test() {
        return "hello world";
    }

    @GetMapping(value = "/list")
    public ResponseData list(@RequestParam(required = false) String name
            , @RequestParam(defaultValue = "1") int pageNum
            , @RequestParam(defaultValue = "10") int pageSize) {
        ResponseData responseData = ResponseData.createResponseData();
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
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
}
