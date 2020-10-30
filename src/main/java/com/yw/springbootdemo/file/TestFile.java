package com.yw.springbootdemo.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author yangwei
 * @date 2019/6/12 17:21
 */
public class TestFile {
    public static void main(String[] args) {
//        File file = new File("F:/test");
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        System.out.println(file.getAbsolutePath());
//        System.out.println("判断是否存在："+file.exists());
//        System.out.println("判断是否是文件夹："+file.isDirectory());
//        File file1 = new File("F:/test/test.txt");
//        if (!file1.exists()) {
//            file1.createNewFile();
//        }
        File file = new File("F:/test.txt");
        try(OutputStream out = new FileOutputStream(file, true);
            Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            String s = "Hello World";
//            byte[] bytes = s.getBytes();
//            out.write(bytes);
            writer.write("Hello World");
            writer.write("\r\n");
        } catch (IOException e) {
            System.out.println();
        }
    }
}
