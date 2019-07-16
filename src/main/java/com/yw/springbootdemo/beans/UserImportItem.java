package com.yw.springbootdemo.beans;

import com.yw.springbootdemo.model.User;

import java.io.Serializable;

/**
 * @author yangwei
 * @date 2019/6/5 11:06
 */
public class UserImportItem implements Serializable {
    private static final long serialVersionUID = -2521816527592841677L;
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String email;

    public static User convertToUser(UserImportItem item) {
        User user = new User();
        user.setName(item.getName());
        user.setEmail(item.getEmail());
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
