package com.yw.springbootdemo.thinking_in_java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yangwei
 * @date 2019/9/16 14:50
 */
public class BufferedInputFile {

    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader(fileName));
        String s;
        StringBuffer sb = new StringBuffer();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("F:/Workspace/springboot-demo" +
                "/src/main/java/com/yw/springbootdemo/thinking_in_java/io/BufferedInputFile.java"));
    }
}
