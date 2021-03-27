package com.sour.java.thread.project.saletask;

/**
 *  秒杀商品任务
 *
 *
 * @author xgl
 * @date 2021/3/27 12:43
 **/
public class SaleTask implements Runnable{

    /**
     *  商品的数量
     */
    private static int productNum = 10;

    /**
     *  客户的名称
     */
    private String userName;

    public SaleTask(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(userName + " 正在使用 " + threadName + "参与秒杀活动");
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        synchronized (SaleTask.class) {
            if ( productNum > 0 ) {
                System.out.println(userName + "用户使用" + threadName
                        + " 已经秒杀了第 " + productNum + " 商品  ----------- 成功 " + userName);
                productNum--;
            } else {
                System.out.println(userName + "用户使用" + threadName
                        + " 秒杀失败!  ----------- 失败 " + userName);
            }
        }
    }
}
