package com.yw.springbootdemo.thinking_in_java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwei
 * @date 2019/8/26 14:12
 */
public class Ex3 {
    public static void main(String[] args) {
        ExecutorService exec1 = Executors.newCachedThreadPool();
        exec1.execute(new Ex3RunnerA());
        exec1.execute(new Ex3RunnerB());
        exec1.execute(new Ex3RunnerC());
        exec1.shutdown();

        ExecutorService exec2 = Executors.newFixedThreadPool(3);
        exec2.execute(new Ex3RunnerA());
        exec2.execute(new Ex3RunnerB());
        exec2.execute(new Ex3RunnerC());
        exec2.shutdown();

        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        exec3.execute(new Ex3RunnerA());
        exec3.execute(new Ex3RunnerB());
        exec3.execute(new Ex3RunnerC());
        exec3.shutdown();
    }
}

class Ex3RunnerA implements Runnable {
    public Ex3RunnerA() {
        System.out.println("Constructing Ex1RunnerA");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerA");
            Thread.yield();
        }
        System.out.println("Ex1RunnerA task complete.");
        return;
    }
}

class Ex3RunnerB implements Runnable {
    public Ex3RunnerB() {
        System.out.println("Constructing Ex1RunnerB");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerB");
            Thread.yield();
        }
        System.out.println("Ex1RunnerB task complete.");
        return;
    }
}

class Ex3RunnerC implements Runnable {
    public Ex3RunnerC() {
        System.out.println("Constructing Ex1RunnerC");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi from Ex1RunnerC");
            Thread.yield();
        }
        System.out.println("Ex1RunnerC task complete.");
        return;
    }
}

