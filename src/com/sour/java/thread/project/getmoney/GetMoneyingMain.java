package com.sour.java.thread.project.getmoney;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 开始取钱
 *
 * @author xgl
 * @date 2021/3/27 13:18
 **/
public class GetMoneyingMain {
    public static void main(String[] args) {
        // 1, 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(1000, new ThreadFactory() {
            /**
             * 第几号ATM
             */
            private int ATM = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ATM:" + ++ATM);
            }
        });

        // 2, 创建任务并提交
        for (int i = 1; i <= 100; i++) {
            GetMoneyTask moneyTask = new GetMoneyTask("客户" + i, 50);
            pool.submit(moneyTask);
        }

        // 3, 关闭线程池
        pool.shutdown();
    }
}
