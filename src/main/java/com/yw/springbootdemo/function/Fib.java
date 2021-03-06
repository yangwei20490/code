package com.yw.springbootdemo.function;

import java.math.BigInteger;

/**
 * @author yangwei
 * @date 2019/5/14 11:31
 */
public class Fib {

    public static void main(String[] args) {

    }

    //计算斐波那契数列的朴素实现，性能极差
    private static BigInteger fib(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }
        return fib(n - 1).add(fib(n - 2));
    }
}
