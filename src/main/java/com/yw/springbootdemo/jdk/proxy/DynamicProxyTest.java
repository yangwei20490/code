package com.yw.springbootdemo.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yangwei
 * @date 2019/6/21 11:08
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        IStudentService target = new StudentService();
        InvocationHandler handler = new MyInvocationHandler(target);
        IStudentService proxyObject = (IStudentService) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                target.getClass().getInterfaces(), handler);
        proxyObject.add("好学生");
    }
}
