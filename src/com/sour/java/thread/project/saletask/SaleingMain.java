package com.sour.java.thread.project.saletask;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 开始秒杀商品活动
 *
 * @author xgl
 * @date 2021/3/27 12:52
 **/
public class SaleingMain {
    public static void main(String[] args) {

        /**
         * 1, ArrayList和ArrayBlockingQueue一样，内部基于数组来存放元素
         * 2, LinkedBlockingQueue实现了BlockingQueue接口
         */

        // 1, 创建线程池对象
        ThreadPoolExecutor pool
                = new ThreadPoolExecutor(3, 10000, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(15));

        // 2, 创建任务对象
        for (int i = 1; i <= 1000; i++) {
            SaleTask task = new SaleTask("客户" + i);
            pool.submit(task);
        }

        // 3, 关闭线程池
        pool.shutdown();
    }
}
