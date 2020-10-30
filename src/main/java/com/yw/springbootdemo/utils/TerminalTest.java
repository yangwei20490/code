package com.yw.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-09-28 14:30
 */
public class TerminalTest {

    public static List<String> getList(String path) {
        String s = JsonUtil.readJsonFile(path);
        JSONObject json = JSON.parseObject(s);
        JSONObject data = json.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("dataList");
        List<String > list = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject key1 = (JSONObject) o;
//            Object o = key1.get("vehicleStatus");
//            JSONArray array = data.getJSONArray("vehicleStatus");
            List<String> statuses = (List) key1.getJSONArray("vehicleStatus");
            list.add(statuses.get(7));
//            System.out.println(statuses);
        }
        list.forEach(System.out::println);
        System.out.println(list.size());
        return list;
    }

    public static void main(String[] args) {
        TerminalTest.getList("C:\\Users\\yangwei\\Desktop\\场地终端偏离\\201719\\201719(2020-09-28 063930~2020-09-28 065938).json");
    }
}
