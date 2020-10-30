package com.yw.springbootdemo.queue;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingDeque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yangwei
 * @date 2020-08-02 10:05
 */
public final class EvictingDeque<E> extends ForwardingDeque<E> {
    private final ArrayDeque<E> delegate;
    final int maxSize;
    private EvictingDeque(int maxSize) {
        this.delegate = new ArrayDeque<>(maxSize);
        this.maxSize = maxSize;
    }

    public static <E> EvictingDeque<E> create(int maxSize) {
        return new EvictingDeque<>(maxSize);
    }

    @Override
    protected Deque<E> delegate() {
        return this.delegate;
    }

    public boolean add(E e) {
        Preconditions.checkNotNull(e);
        if (this.maxSize != 0) {
            if (this.size() == this.maxSize) {
                this.delegate.remove();
            }

            this.delegate.add(e);
        }
        return true;
    }
}
