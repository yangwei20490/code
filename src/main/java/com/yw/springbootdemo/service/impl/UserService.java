package com.yw.springbootdemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.yw.springbootdemo.beans.UserExportItem;
import com.yw.springbootdemo.beans.UserImportItem;
import com.yw.springbootdemo.beans.UserVo;
import com.yw.springbootdemo.mapper.UserMapper;
import com.yw.springbootdemo.model.User;
import com.yw.springbootdemo.queue.QueueGenerationService;
import com.yw.springbootdemo.queue.TestServiceHandler;
import com.yw.springbootdemo.service.IUserService;
import com.yw.springbootdemo.utils.Pinyin4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, User>
        implements IUserService {

    @Autowired
    private QueueGenerationService queueGenerationService;

    @Override
    public void testQueue() {
        queueGenerationService.addData(new TestServiceHandler("小明",5));
    }

    @Override
    public String getEncryptPwd(String salt, String pwd) {
        return DigestUtils.md5Hex(salt.toLowerCase() + pwd);
    }

    @Override
    public void encryptPwd(User user) {
        if (user == null || StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())) {
            return;
        }
        user.setPassword(getEncryptPwd(user.getName(), user.getPassword()));
    }

    @Override
    public void importUsers(UserImportItem item) {
        User user = UserImportItem.convertToUser(item);
        user.setId(System.currentTimeMillis());
        user.setPassword("123456");
        encryptPwd(user);
        save(user);
    }

    @Override
    public void abbreviate() throws BadHanyuPinyinOutputFormatCombination {
        List<User> list = this.list();
//        list.forEach(user -> {
//            Pinyin4j pinyin4j = new Pinyin4j();
//            String s = pinyin4j.toPinYinLowercase(user.getAbbreviate());
//        });
        for (User user : list) {
            Pinyin4j pinyin4j = new Pinyin4j();
            String s = pinyin4j.toPinYinLowercase(user.getName());
            user.setAbbreviate(s);
            this.updateById(user);
        }
    }

    @Override
    public List<UserExportItem> getExportList(UserVo vo) {
        Page<User> page = new Page<>(vo.getPageIdx(), vo.getPageSize());
        List<User> users = this.page(page).getRecords();
        List<UserExportItem> list = new ArrayList<>();
        try {
            for (User user : users) {
                UserExportItem userExportItem = new UserExportItem();
                userExportItem.setName(user.getName());
                File file = new File(user.getPath());
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOut);
                byte[] b = byteArrayOut.toByteArray();
//                userExportItem.setData(b);
                list.add(userExportItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void export() {
        try(OutputStream out = new FileOutputStream("test.xlsx")) {

            ExcelWriter writer = EasyExcel.write(out, UserExportItem.class).build();

            WriteSheet writeSheet = EasyExcel.writerSheet("test").build();

//            分页查询数据
            int pageNumber = 1;
            int pageSize = 5000;
            int dataLength = pageSize;
            List<UserExportItem> resultList; //置list为空，清空内存
            while(dataLength == pageSize){
                UserVo vo = new UserVo();
                vo.setPageIdx(pageNumber);
                vo.setPageSize(pageSize);
                resultList = getExportList(vo);
                if(CollectionUtils.isEmpty(resultList)){
                    //写数据
//                    writer.write(resultList, writeSheet);
                    break;
                }
                dataLength = resultList.size();
                pageNumber++;
                //写数据
                writer.write(resultList, writeSheet);
            }
            writer.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        final Map<String, String> map = Maps.newConcurrentMap();
        map.put("1", "1");
        map.put("1", "2");
        System.out.println(map);
    }
}
