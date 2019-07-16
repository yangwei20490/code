package com.yw.springbootdemo.threads.reentrant_lock;

import com.baomidou.mybatisplus.extension.api.R;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangwei
 * @date 2019/6/27 9:00
 */
public class ReenterLock implements Runnable {

    public static Lock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
            //支持重入锁
            lock.lock();
            try {
                i++;
            } finally {
                //执行两次解锁
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock t = new ReenterLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
