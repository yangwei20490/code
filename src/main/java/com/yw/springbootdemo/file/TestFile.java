package com.yw.springbootdemo.file;

import java.io.File;

/**
 * @author yangwei
 * @date 2019/6/12 17:21
 */
public class TestFile {
    public static void main(String[] args) {
        File file1 = new File("F:/test");
        System.out.println(file1.getAbsolutePath());
        System.out.println("判断是否存在："+file1.exists());
        System.out.println("判断是否是文件夹："+file1.isDirectory());

    }
}
