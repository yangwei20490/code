package com.yw.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangwei
 * @date 2019-11-19 16:24
 */
public final class JsonUtil {

    /**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getList(String path) {
        String s = JsonUtil.readJsonFile(path);
        JSONObject json = JSON.parseObject(s);
        JSONObject data = json.getJSONObject("data");
        JSONArray array = data.getJSONArray("personInfos");
        List<String > list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject key1 = (JSONObject)array.get(i);
            list.add((String) key1.get("id"));
        }

        return list;
    }

    public static void main(String[] args) {
        String path196 = "C:\\Users\\yangwei\\Desktop\\196.txt";
        List<String > list196 = JsonUtil.getList(path196);

        String path197 = "C:\\Users\\yangwei\\Desktop\\197.txt";
        List<String > list197 = JsonUtil.getList(path197);

        List<String> listA = new ArrayList<>(list196);
        listA.removeAll(list197);

        List<String> listB = new ArrayList<>(list197);
        listB.removeAll(list196);

        System.out.println("~~~~~~~~~~~196跟197的不同");
        System.out.println(Arrays.toString(listA.toArray()));
        System.out.println("~~~~~~~~~~~197跟196的不同");
        System.out.println(Arrays.toString(listB.toArray()));
    }
}
