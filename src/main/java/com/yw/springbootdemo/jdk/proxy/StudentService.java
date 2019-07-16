package com.yw.springbootdemo.jdk.proxy;

/**
 * @author yangwei
 * @date 2019/6/21 10:51
 */
public class StudentService implements IStudentService {
    @Override
    public void add(String name) {
        System.out.println("向数据库中插入名为：  " + name + " 的学生");
    }
}
