package com.feifei.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁Contdition示例
 * Condition与ReentrantLock绑定，有线程等待await()方法、唤醒线程signal()
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
           lock.lock();
           condition.await();
            System.out.println("Thread id going on!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);
        t1.start();
        Thread.sleep(2000);
        //通知t1继续执行
        lock.lock();
        condition.signal();
        //释放锁后t1线程才能重新获取锁
        lock.unlock();
    }
}
