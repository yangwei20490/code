package com.yw.springbootdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangwei
 * @date 2019/5/16 15:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TestAnnotation {
    /*
    注解的属性也叫做成员变量。注解只有成员变量，没有方法。
    注解的成员变量在注解的定义中以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
    在注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组。
    注解中属性可以有默认值，默认值需要用 default 关键值指定。
     */
    int id() default -1;
    String msg() default "hi";
}
