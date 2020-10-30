package com.yw.springbootdemo.thinking_in_java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwei
 * @date 2019/8/26 14:35
 */
public class Ex4 {
    public static void main(String[] args) {
        ExecutorService exec1 = Executors.newCachedThreadPool();
        exec1.execute(new Ex4FibonacciA(15));
        exec1.execute(new Ex4FibonacciB(15));
        exec1.execute(new Ex4FibonacciC(15));
        exec1.execute(new Ex4FibonacciD(15));
        exec1.shutdown();

        ExecutorService exec2 = Executors.newFixedThreadPool(4);
        exec2.execute(new Ex4FibonacciA(15));
        exec2.execute(new Ex4FibonacciB(15));
        exec2.execute(new Ex4FibonacciC(15));
        exec2.execute(new Ex4FibonacciD(15));
        exec2.shutdown();

        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        exec3.execute(new Ex4FibonacciA(15));
        exec3.execute(new Ex4FibonacciB(15));
        exec3.execute(new Ex4FibonacciC(15));
        exec3.execute(new Ex4FibonacciD(15));
        exec3.shutdown();
    }
}

class Ex4FibonacciA implements Runnable {
    private int n;

    Ex4FibonacciA(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + "");
            System.out.println();
        }
    }
}

class Ex4FibonacciB implements Runnable {
    private int n;

    Ex4FibonacciB(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + "");
            System.out.println();
        }
    }
}

class Ex4FibonacciC implements Runnable {
    private int n;

    Ex4FibonacciC(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + "");
            System.out.println();
        }
    }
}

class Ex4FibonacciD implements Runnable {
    private int n;

    Ex4FibonacciD(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + "");
            System.out.println();
        }
    }
}

