package com.yw.springbootdemo.redis;

import redis.clients.jedis.Jedis;

/**
 * @author yangwei
 * @date 2019/6/8 16:00
 */
public class RedisJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.28.128.3");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }
}
