package com.feifei.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待限时
 * ReentrantLock.tryLock(timeout，timeUnit)尝试获取锁，可设置时间超时没获取到则返回false
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+" get lock successful!"
                    + System.currentTimeMillis());
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName()+" get lock faild!"
                    + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
