package com.sour.java.thread.demo01;

/**
 * 需求:
 *  自定义线程池练习, 这是任务类
 *  包含任务编号, 每一个任务执行时间设计为0.2秒
 *
 * @author xgl
 * @date 2021/3/21 16:55
 **/
public class MyTask implements Runnable {

    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("当前线程: " + name + " 即将执行任务, id: " + id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" 线程: " + name + ", id: " + id + " 完成任务!");
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id=" + id +
                '}';
    }
}
