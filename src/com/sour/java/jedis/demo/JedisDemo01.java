package com.sour.java.jedis.demo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Jedis String
 *
 * @author xgl
 * @date 2021/5/23 15:41
 **/
public class JedisDemo01 {

    public static void main(String[] args) {
        // 创建Jedis对象
        Jedis jedis = new Jedis("192.168.0.105", 6379);
        // 测试
        String ping = jedis.ping();
        System.out.println(ping);


//        获取所有
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);

//        添加数据
        for (int i = 0; i < 10; i++) {
            jedis.set("k" + i, String.valueOf(i));
        }
//        获取数据
        String k2 = jedis.get("k2");
        System.out.println(k2);

//        设置多个key
        jedis.mset("k100", "v100", "k101", "v101");
        List<String> mget = jedis.mget("k100", "k101");
        System.out.println(mget);


    }
}
