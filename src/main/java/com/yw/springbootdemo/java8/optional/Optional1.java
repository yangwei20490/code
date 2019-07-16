package com.yw.springbootdemo.java8.optional;

import java.util.Optional;

/**
 * @author yangwei
 * @date 2019/5/13 15:20
 */
public class Optional1 {

    public static void print(String text) {
        Optional.ofNullable(text).ifPresent(System.out::println);
    }

    public static int getLength(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }

    public static void main(String[] args) {
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));
    }
}
