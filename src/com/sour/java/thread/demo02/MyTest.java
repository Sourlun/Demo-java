package com.sour.java.thread.demo02;

/**
 * 1) 获取线程池对象ExecutorService：
 *      每提交一个任务就创建一个线程
 *           static ExecutorService newCachedThreadPool()
 *               创建一个默认的线程池对象,里面的线程可重用,且在第一次使用时才创建
 *
 *           static ExecutorService newCachedThreadPool(ThreadFactorythreadFactory)
 *               线程池中的所有线程都使用ThreadFactory来创建,这样的线程无需手动启动,自动执行;
 *
 *      可以创建固定数量的线程池
 *           static ExecutorService newFixedThreadPool(int nThreads)
 *               创建一个可重用固定线程数的线程池
 *
 *           static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
 *               创建一个可重用固定线程数的线程池且线程池中的所有线程都使用ThreadFactory来创建。
 *
 *      整个线程池只有一个线程，任务需要排队来进行处理
 *           static ExecutorService newSingleThreadExecutor()
 *               创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
 *
 *           static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory)
 *               创建一个使用单个 worker 线程的 Executor，且线程池中的所有线程都使用ThreadFactory来创建。
 *
 * 2)ExecutorService线程对象的方法：
 *      void shutdown()
 *          启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
 *
 *      List<Runnable> shutdownNow()
 *          停止所有正在执行的任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
 *
 *      <T> Future<T> submit(Callable<T> task)
 *          执行带返回值的任务，返回一个Future对象。
 *
 *      Future<?> submit(Runnable task)
 *          执行 Runnable 任务，并返回一个表示该任务的 Future。
 *
 *      <T> Future<T> submit(Runnable task, T result) 执行 Runnable
 *          任务，并返回一个表示该任务的 Future。
 */


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 练习 Executors
 *      获取ExecutorService, 然后调用方法, 提交任务
 *
 * @author xgl
 * @date 2021/3/21 18:12
 **/
public class MyTest {

    public static void main(String[] args) {
        // 创建一个默认的线程池对象,里面的线程可重用,且在第一次使用时才创建
//        test01();

        // 线程池中的所有线程都使用ThreadFactory来创建,这样的线程无需手动启动,自动执行;
        test02();

    }

    /**
     *  创建一个默认的线程池对象,里面的线程可重用,且在第一次使用时才创建
     *
     * @author xgl
     * @date 2021/3/21 18:25
     **/
    private static void test01() {
        // 1, 利用工厂类获取线程池对象
        ExecutorService ex = Executors.newCachedThreadPool();

        // 2, 提交任务
        for (int i = 0; i < 10; i++) {
            ex.submit(new MyRunnable(i));
        }
    }


    /**
     *  线程池中的所有线程都使用ThreadFactory来创建,这样的线程无需手动启动,自动执行;
     *
     * @author xgl
     * @date 2021/3/21 18:25
     **/
    private static void test02() {

        // 1, 利用工厂类获取线程池对象
        ExecutorService ex = Executors.newCachedThreadPool(new ThreadFactory() {
            int n = 1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称: " + n++);

            }
        });

        // 2, 提交任务
        for (int i = 0; i < 10; i++) {
            ex.submit(new MyRunnable(i));
        }
    }
}


/**
 *  任务类
 *      包含任务编号
 */
class MyRunnable implements Runnable {

    /**
     *  任务编号
     */
    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // 获取线程的名称
        String name = Thread.currentThread().getName();
        System.out.println(name + " 执行了任务! " + id);
    }
}