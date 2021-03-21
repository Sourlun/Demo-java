package com.sour.java.thread.demo01;

/**
 *  测试类
 *      1, 创建线程池类对象;
 *      2, 提交多个任务
 *
 * @author xgl
 * @date 2021/3/21 17:28
 **/
public class MyTest {
    public static void main(String[] args) {

        /**
         *  执行一个任务: 0.1秒
         *
         */

        // 创建线程池类对象
        MyThreadPool pool = new MyThreadPool(2 , 4, 20);

        // 提交多个任务
        for (int i = 0; i < 100; i++) {
            // 创建任务对象, 提交给线程池
            MyTask myTask = new MyTask(i);
            pool.submit(myTask);
        }
    }
}
