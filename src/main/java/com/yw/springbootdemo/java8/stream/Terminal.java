package com.yw.springbootdemo.java8.stream;

import java.util.stream.Stream;

/**
 * @author yangwei
 * @date 2019/5/23 14:58
 */
public class Terminal {
    public static void main(String[] args) {
        /*count
        * count方法将返回Stream中元素的个数。*/
        long count = Stream.of(1, 2, 3, 4, 5).count();
        System.out.println("count: " + count);
        System.out.println("↑↑↑↑↑ peek ↑↑↑↑↑");

        Stream.of(5, 2, 3, 1, 4)
                .forEach(System.out::println);

        Stream.of(5, 2, 3, 1, 4)
                .forEachOrdered(System.out::println);
    }
}
