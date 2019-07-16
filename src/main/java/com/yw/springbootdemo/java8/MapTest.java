package com.yw.springbootdemo.java8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangwei
 * @date 2019/6/24 11:16
 */
public class MapTest {
    public static void main(String[] args) {
        String ret;
        Map<String, String> map = new ConcurrentHashMap<>();
        ret = map.putIfAbsent("a", "aaa");
    }
}
