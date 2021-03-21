package com.sour.java.thread.demo01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义的线程池类
 *
 * @author xgl
 * @date 2021/3/21 17:08
 **/
public class MyThreadPool {

    /**
     *  任务队列
     *      需要控制线程安全问题
     */
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());

    /**
     *  当前线程数量
     */
    private int num;

    /**
     *  核心线程数量
     *      任务数 * 每个任务执行的时间
     */
    private int corePoolSize;

    /**
     *  最大线程数量
     *      (最大任务数 - 任务队列长度) * 单个任务执行时间
     */
    private int maxSize;

    /**
     *  任务队列的长度
     *      核心线程数 / 单个任务执行时间 * 2
     */
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    /**
     *  提交任务
     * @param r
     */
    public void submit(Runnable r) {
        // 判断当前集合中任务的数量, 是否超出了最大的任务数量
        if ( tasks.size() >= workSize ) {
            System.out.println("任务: " + r + " 被丢弃了!");
        } else {
            tasks.add(r);
            // 执行任务
            execTask(r);
        }
    }

    /**
     *  执行任务
     * @param r
     */
    private void execTask(Runnable r) {
        // 判断当前线程池中的线程总数量, 是否超出核心数
        if ( num < corePoolSize ) {
            new MyWorker("核心线程:" + num, tasks).start();
            num++;
        } else if ( num < maxSize ) {
            new MyWorker("非核心线程:" + num, tasks).start();
            num++;
        } else {
            System.out.println("任务:" + r + " 被缓存了!");
        }
    }

}
