package com.yw.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.springbootdemo.beans.UserImportItem;
import com.yw.springbootdemo.model.User;

public interface IUserService extends IService<User> {

    String getEncryptPwd(String salt, String pwd);

    void encryptPwd(User user);

    void importUsers(UserImportItem item);
}
