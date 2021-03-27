package com.sour.java.thread.demo03;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 测试 ScheduleExecutorService接口中 延时执行任务 和 重复执行任务 的功能
 *
 *  ScheduleExecutorService 具备: 1, 延时执行任务;  2, 重复执行任务
 *
 * @author xgl
 * @date 2021/3/27 11:18
 **/
public class ScheduleExecutorServiceDemo01 {

    public static void main(String[] args) {

        // 延迟执行线程
//        newScheduledThreadPool();

        // 延迟执行线程 02 线程可以自定义
//        newScheduledThreadPool02();

        // 单线程 延迟执行线程 03 线程可以自定义
        newSingleThreadScheduledExecutor03();

        System.out.println("Main OVER!");
    }

    /**
     * 延迟执行线程 01
     *
     * @author xgl
     * @date 2021/3/27 11:32
     **/
    private static void newScheduledThreadPool() {
        // 1, 获取一个具备 延迟执行任务 的线程池对象
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);

        // 2, 创建多个任务对象, 提交任务, 每个任务延迟2秒执行
        for (int i = 0; i < 10; i++) {
            es.schedule(new MyRunnbale(i), 2, TimeUnit.SECONDS);
        }
    }


    /**
     * 延迟执行线程 02
     *      线程可以自定义
     *
     * @author xgl
     * @date 2021/3/27 11:32
     **/
    private static void newScheduledThreadPool02() {
        // 1, 获取一个具备 延迟执行任务 的线程池对象
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private int n = 0;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名: " + n++);
            }
        });

        // 2, 创建多个任务对象, 提交任务, 每个任务延迟1秒执行, 每个任务间隔2秒执行
        es.scheduleWithFixedDelay(new MyRunnbale(1), 1,2, TimeUnit.SECONDS);
    }


    /**
     * 单线程 延迟执行线程 03
     *      线程可以自定义
     *
     * @author xgl
     * @date 2021/3/27 11:32
     **/
    private static void newSingleThreadScheduledExecutor03() {
        // 1, 获取一个具备 延迟执行任务 的线程池对象
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor( new ThreadFactory() {
            private int n = 0;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名: " + n++);
            }
        });

        // 2, 创建多个任务对象, 提交任务, 每个任务延迟1秒执行, 每个任务运行1.5秒, 每个任务间隔2秒执行
        es.scheduleWithFixedDelay(new MyRunnbale(1), 1,2, TimeUnit.SECONDS);
    }
}

class MyRunnbale implements Runnable {

    /**
     *  线程id
     */
    private int id;

    public MyRunnbale(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行了任务: " + threadName + "  id: " + id);
    }
}
