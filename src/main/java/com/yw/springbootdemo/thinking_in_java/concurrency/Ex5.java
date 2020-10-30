package com.yw.springbootdemo.thinking_in_java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangwei
 * @date 2019/8/26 14:52
 */
public class Ex5 {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public int next() {
        return atomicInteger.addAndGet(2);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            results.add(exec.submit(new Ex5Fibonacci(i)));
        }
        results.forEach(fs -> {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        });
    }
}

class Ex5Fibonacci implements Callable<Integer> {
    private int n;

    public Ex5Fibonacci(int n) {
        this.n = n;
    }

    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += fib(i);
        }
        return result;
    }
}
