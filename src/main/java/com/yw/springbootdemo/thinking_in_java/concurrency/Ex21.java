package com.yw.springbootdemo.thinking_in_java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yangwei
 * @date 2019/8/27 10:11
 */
public class Ex21 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        B b = new B(new A());
        exec.execute(b.getA());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("main() sleep interrupted");
        }
        exec.shutdown();
    }
}

class A implements Runnable {
    private volatile boolean signal = Boolean.FALSE;
    @Override
    public synchronized void run() {
        try {
            while (!signal) {
                System.out.println("A.run() wait()");
                wait();
                signal = Boolean.TRUE;
                System.out.println("A is done waiting");
            }
        } catch (InterruptedException e) {
            System.out.println("A run() interrupted");
        } finally {
            System.out.println("Leaving A.run()");
        }
    }
    public synchronized void message() {
        System.out.println("Hi from A");
    }
}

class B implements Runnable {
    private A a;
    public A getA() {
        return a;
    }
    B(A a) {
        this.a = a;
    }
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            synchronized(a) {
                System.out.println("B.run() a.notifyAll()");
                a.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
