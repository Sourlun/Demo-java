package com.sour.java.jedis.demo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Jedis Set
 *
 * @author xgl
 * @date 2021/5/23 15:41
 **/
public class JedisDemo03 {

    public static void main(String[] args) {
        // 创建Jedis对象
        Jedis jedis = new Jedis("192.168.0.105", 6379);

//        添加数据
        jedis.sadd("kSet", "a", "b", "c", "a");

//        获取数据
        Set<String> kSet = jedis.smembers("kSet");
        System.out.println(kSet);
    }
}
