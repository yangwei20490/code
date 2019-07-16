package com.yw.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.springbootdemo.beans.UserImportItem;
import com.yw.springbootdemo.mapper.UserMapper;
import com.yw.springbootdemo.model.User;
import com.yw.springbootdemo.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User>
        implements IUserService {

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
}
