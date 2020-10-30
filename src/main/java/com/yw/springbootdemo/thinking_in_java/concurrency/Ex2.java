package com.yw.springbootdemo.thinking_in_java.concurrency;

/**
 * @author yangwei
 * @date 2019/8/26 13:56
 */
public class Ex2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Ex2FibonacciA(15));
        Thread thread2 = new Thread(new Ex2FibonacciB(15));
        Thread thread3 = new Thread(new Ex2FibonacciC(15));
        Thread thread4 = new Thread(new Ex2FibonacciD(15));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class Ex2FibonacciA implements Runnable {
    private int n;

    Ex2FibonacciA(int n) {
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

class Ex2FibonacciB implements Runnable {
    private int n;

    Ex2FibonacciB(int n) {
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

class Ex2FibonacciC implements Runnable {
    private int n;

    Ex2FibonacciC(int n) {
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

class Ex2FibonacciD implements Runnable {
    private int n;

    Ex2FibonacciD(int n) {
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
