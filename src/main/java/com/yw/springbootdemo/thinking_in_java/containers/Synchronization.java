package com.yw.springbootdemo.thinking_in_java.containers;

import java.util.*;

/**
 * @author yangwei
 * @date 2019/8/22 16:18
 */
public class Synchronization {
    public static void main(String[] args) {
        Collection<String> c = Collections
                .synchronizedCollection(new ArrayList<>());
        List<String> list = Collections
                .synchronizedList(new ArrayList<>());
        Set<String> s = Collections
                .synchronizedSet(new HashSet<>());
        Set<String> ss = Collections
                .synchronizedSortedSet(new TreeSet<>());
        Map<String, String> m = Collections
                .synchronizedMap(new HashMap<>());
        Map<String, String> sm = Collections
                .synchronizedSortedMap(new TreeMap<>());
    }
}
