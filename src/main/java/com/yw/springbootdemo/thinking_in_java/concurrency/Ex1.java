package com.yw.springbootdemo.thinking_in_java.concurrency;

/**
 * @author yangwei
 * @date 2019/8/26 11:07
 */
public class Ex1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Ex1RunnerA());
        Thread thread2 = new Thread(new Ex1RunnerB());
        Thread thread3 = new Thread(new Ex1RunnerC());
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class Ex1RunnerA implements Runnable {
    public Ex1RunnerA() {
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

class Ex1RunnerB implements Runnable {
    public Ex1RunnerB() {
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

class Ex1RunnerC implements Runnable {
    public Ex1RunnerC() {
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
