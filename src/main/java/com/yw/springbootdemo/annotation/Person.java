package com.yw.springbootdemo.annotation;

import java.lang.annotation.Repeatable;

/**
 * @author yangwei
 * @date 2019/5/16 16:03
 */
@Repeatable(Persons.class)
public @interface Person {
    String role() default "";
}
