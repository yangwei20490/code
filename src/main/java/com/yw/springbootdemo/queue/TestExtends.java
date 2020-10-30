package com.yw.springbootdemo.queue;

import java.util.ArrayDeque;

/**
 * @author yangwei
 * @date 2020-08-03 16:51
 */
public class TestExtends<E> extends ArrayDeque {

    private final ArrayDeque<E> delegate;
    final int maxSize;
    private TestExtends(int maxSize) {
        this.delegate = new ArrayDeque<>(maxSize);
        this.maxSize = maxSize;
    }
}
