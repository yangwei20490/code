package com.yw.springbootdemo.reflection;

/**
 * @author yangwei
 * @date 2019/5/24 10:56
 * 获取Class对象的三种方式
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)

 */
public class Reflex {
    public static void main(String[] args) {
        //第一种方式获取Class对象
        Student stu = new Student();
        Class stuClass = stu.getClass();
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("com.yw.springbootdemo.reflection.Student");
            System.out.println(stuClass2 == stuClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
