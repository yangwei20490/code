package com.yw.springbootdemo.algorithm;

/**
 * @author yangwei
 * @date 2019-12-23 15:14
 */
public class test {

    public void reverseString(char[] c) {
        String s = new StringBuilder(c.toString()).reverse().toString();
        System.out.println(s);
    }
    public static void main(String[] args) {
        char[] c = {'H','a','n','n','a','h'};
        char[] c1 = new StringBuilder(String.valueOf(c)).reverse().toString().toCharArray();
        System.out.println(c1);
    }
}
