package com.sour.java.jedis.project;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 手机验证码
 *
 * @author xgl
 * @date 2021/5/23 16:15
 **/
public class PhoneCode {

    public static void main(String[] args) {

        String code = getCode();
        System.out.println(code);
    }

    private static void verifyCode(String phone) {
        Jedis jedis = new Jedis("192.168.0.105", 6379);
        // 拼接key
        String countKey = "VerifyCode" + phone + ":count";
        // 验证码
        String codeKey = "VerifyCode" + phone + ":code";

        // 每个手机每天只能发送三个
        String count = jedis.get(countKey);
        if ( null == count ) {
            // 没有发送次数, 第一次发送
            jedis.setex(countKey, 24*60*60, "1");
        } else if (Integer.parseInt(count) <= 2 ) {
            // 发送次数++
            jedis.incr(countKey);
        } else if ( Integer.parseInt(count) > 2 ){
            // 已经发送三次, 不能发送了
            System.out.println("今天发送次数已经超过三次");
        }

        // 验证码放到redis里面
        String code = getCode();
        jedis.setex(codeKey, 120, code);

        jedis.close();
    }

    public static void getRedisCode(String phone, String code) {
        Jedis jedis = new Jedis("192.168.0.105", 6379);
        // 验证码
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        // 判定
        if ( redisCode.equals(code)) {
            System.out.println("成功!");
        } else {
            System.out.println("失败");
        }

        jedis.close();
    }

    /**
     * 六位数随机数
     *
     * @author xgl
     * @date 2021/5/23 16:18
     **/
    private static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
