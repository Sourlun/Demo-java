package com.sour.java.thread.demo01;

import java.util.List;

/**
 * 设计一个集合, 用于保存所有任务
 *      在这里当作: 核心线程, 非核心线程
 *
 *
 * @author xgl
 * @date 2021/3/21 17:02
 **/
public class MyWorker extends Thread {

    /**
     *  线程名字
     */
    private String name;

    /**
     *  所有任务
     */
    private List<Runnable> tasks;


    public MyWorker(String name, List<Runnable> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     *  判断集合中是否有任务
     *      有 -> 一直执行
     */
    @Override
    public void run() {
        while ( tasks.size() > 0 ) {
            Runnable task = tasks.remove(0);
            task.run();
        }
    }
}
