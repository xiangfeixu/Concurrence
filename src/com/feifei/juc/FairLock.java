package com.feifei.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁 ReentrantLock的有参构造函数的参数fair设置为true
 * 在锁的获取上，每个线程是公平的
 * 锁会被分配给等待队列中的每个线程
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class FairLock implements Runnable{
    private static ReentrantLock fairLock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(System.currentTimeMillis()+Thread.currentThread().getName()+"获取锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock1 = new FairLock();
        FairLock fairLock2 = new FairLock();
        Thread t1 = new Thread(fairLock1);
        Thread t2 = new Thread(fairLock2);
        t1.start();
        t2.start();
    }
}
