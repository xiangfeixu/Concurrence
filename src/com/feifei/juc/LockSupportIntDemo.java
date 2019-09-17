package com.feifei.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport中断响应测试
 * park（）可以响应中断，不会抛出interruptException异常
 * @author xuxiangfei
 * @date 2019/1/7
 */
public class LockSupportIntDemo {
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (ChangeObjectThread.class) {
                System.out.println("in "+getName());
                LockSupport.park();
                if (Thread.interrupted()) {
                    System.out.println(getName()+"被中断了");
                }
            }
            System.out.println(getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}