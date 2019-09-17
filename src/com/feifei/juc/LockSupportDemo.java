package com.feifei.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞工具类
 * park（）阻塞线程、unpark（）解除阻塞
 * @author xuxiangfei
 * @date 2019/1/7
 */
public class LockSupportDemo {

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (ChangeObjectThread.class) {
                System.out.println("in "+getName()+ " 开始时间："+System.currentTimeMillis());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
