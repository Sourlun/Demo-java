package com.sour.java.thread.project.getmoney;

/**
 * 取钱任务
 *
 * @author xgl
 * @date 2021/3/27 13:11
 **/
public class GetMoneyTask implements Runnable{

    /**
     *  用户姓名
     */
    private String userName;

    /**
     *  取款金额
     */
    private double money;

    /**
     *  总金额
     */
    private static double totalMoney = 1000;

    public GetMoneyTask(String userName, double money) {
        this.userName = userName;
        this.money = money;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(userName + " 正在准备使用 " + threadName + " 取钱");
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        synchronized ( GetMoneyTask.class ) {
            if ( totalMoney > 0 && totalMoney >= money ) {
                totalMoney -= money;
                System.out.println(userName + " 使用 " + threadName + " 取钱成功! "
                        + money + " 剩余: " + totalMoney);
            } else {
                System.out.println(userName + "取钱, 余额不足: " + totalMoney);
            }
        }
    }
}
