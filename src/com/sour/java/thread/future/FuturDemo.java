package com.sour.java.thread.future;

import java.util.concurrent.*;

/**
 * 异步计算结果
 *
 * @author xgl
 * @date 2021/3/27 12:14
 **/
public class FuturDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 单线程 异步获取结果
//        future01();

        // 单线程 异步获取结果:  1,取消任务  2,设置等待时间
        future02();
    }

    /**
     * 单线程 异步获取结果
     *
     * @author xgl
     * @date 2021/3/27 12:27
     **/
    private static void future01() throws InterruptedException, ExecutionException {
        // 1, 获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();

        // 2, 创建Callable 类型任务对象
        Future<Integer> f = es.submit(new MyCallable(1, 1));

        // 3, 判断任务是否完成, 取消
        boolean done = f.isDone();
        System.out.println("第一次判断任务是否完成: " + done);
        boolean cancel = f.isCancelled();
        System.out.println("第一次判断任务是否取消: " + cancel);

        // 4, 任务执行结果v  (一直等待, 直到完成为止)
        Integer v = f.get();
        System.out.println("任务执行结果: " + v);

        // 5, 判断任务是否完成, 取消
        boolean done2 = f.isDone();
        System.out.println("第二次判断任务是否完成: " + done2);
        boolean cancel2 = f.isCancelled();
        System.out.println("第二次判断任务是否取消: " + cancel2);
    }


    /**
     * 单线程 异步获取结果
     *  1, 取消任务
     *  2, 设置等待时间
     *
     * @author xgl
     * @date 2021/3/27 12:27
     **/
    private static void future02() throws InterruptedException, ExecutionException {
        // 1, 获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();

        // 2, 创建Callable 类型任务对象
        Future<Integer> f = es.submit(new MyCallable(1, 1));

        // 3, 取消任务
        boolean cancel = f.cancel(true);
        System.out.println("第一次取消任务结果: " + cancel);

        // 3.2 设置等待时间  ( 任务执行2秒, 这里等待1秒. 会抛java.util.concurrent.CancellationException异常 )  (任务来不及执行完成)
        Integer v1;
        try {
            v1 = f.get(1, TimeUnit.SECONDS);
            System.out.println("设置等待时间1秒, 结果: " + v1);
        } catch (TimeoutException e) { e.printStackTrace(); }
    }
}


class MyCallable implements Callable<Integer> {

    private int a;

    private int b;

    public MyCallable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println( threadName + " 准备开始计算...  ");
        Thread.sleep(2000);
        System.out.println( threadName + " 计算完成...  ");
        return a + b;
    }
}