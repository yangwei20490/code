package com.yw.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.springbootdemo.beans.UserExportItem;
import com.yw.springbootdemo.beans.UserImportItem;
import com.yw.springbootdemo.beans.UserVo;
import com.yw.springbootdemo.model.User;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

public interface IUserService extends IService<User> {

    String getEncryptPwd(String salt, String pwd);

    void encryptPwd(User user);

    void importUsers(UserImportItem item);

    void abbreviate() throws BadHanyuPinyinOutputFormatCombination;

    List<UserExportItem> getExportList(UserVo vo);

    void export();

    void testQueue();
}
