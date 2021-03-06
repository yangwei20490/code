package com.yw.springbootdemo.function;

import com.google.common.collect.Lists;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author yangwei
 * @date 2019/5/14 9:44
 */
public class HighOrderFunctions {
    private static <T> Predicate<T> notEqual(T t) {
        return (v) -> !Objects.equals(v, t);
    }

    public static void main(String[] args) {
        Lists.newArrayList(1, 2, 3)
                .stream().filter(notEqual(1))
                .forEach(System.out::println);
    }
}
