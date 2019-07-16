package com.yw.springbootdemo.utils;

/**
 * @author yangwei
 * @date 2019/6/18 10:27
 */
public class test {
    public static void main(String[] args) {
//        int a = 0x502c + 0x8;
//        System.out.println(a);
//        System.out.println(0b100);
//
//        System.out.println(CommonUtil.checkMobileFormat("13720160296"));
        int valueTen = 131072;
        String strHex = Integer.toHexString(valueTen);
        System.out.println(strHex);
    }
}
