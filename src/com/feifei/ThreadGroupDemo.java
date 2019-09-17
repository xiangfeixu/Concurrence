package com.feifei;

import java.sql.Timestamp;

/**
 * 线程组ThreadGroup测试类
 * @author xuxiangfei
 * @date 2018/12/29
 */
public class ThreadGroupDemo implements Runnable {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroupName");
        Thread t1 = new Thread(threadGroup, new ThreadGroupDemo(), "T1");
        Thread t2 = new Thread(threadGroup, new ThreadGroupDemo(), "T2");
        t1.start();
        t2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "_" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName + new Timestamp(System.currentTimeMillis()).toString());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
