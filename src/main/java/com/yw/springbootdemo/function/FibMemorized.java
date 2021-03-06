package com.yw.springbootdemo.function;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @date 2019/5/14 11:38
 */
public class FibMemorized {

    public static void main(String[] args) {
        System.out.println(fib(40));
    }

    //使用记忆化的斐波那契数列计算
    private static Map<Integer, BigInteger> lookupTable = new HashMap<>();

    static {
        lookupTable.put(0, BigInteger.ZERO);
        lookupTable.put(1, BigInteger.ONE);
    }

    private static BigInteger fib(int n) {
        if (lookupTable.containsKey(n)) {
            return lookupTable.get(n);
        } else {
            BigInteger result = fib(n -1 ).add(fib(n - 2));
            lookupTable.put(n, result);
            return result;
        }
    }
}
