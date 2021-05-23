package com.sour.java.jedis.demo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Jedis List
 *
 * @author xgl
 * @date 2021/5/23 15:41
 **/
public class JedisDemo02 {

    public static void main(String[] args) {
        // 创建Jedis对象
        Jedis jedis = new Jedis("192.168.0.105", 6379);

//        添加数据
        String[] values = new String[10];
        for (int i = 0; i < 10; i++) {
            values[i] = String.valueOf(i);
        }
        jedis.lpush("kList", values);

//        获取数据
        List<String> kList = jedis.lrange("kList", 0, -1);
        System.out.println(kList);
    }
}
