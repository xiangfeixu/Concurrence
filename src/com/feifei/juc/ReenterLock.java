package com.feifei.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock重入锁用法示例
 * 可多次获取锁和释放锁 但必须次数对应
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class ReenterLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0;j<10000000;j++){
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally{
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i的值为："+i);
    }
}
