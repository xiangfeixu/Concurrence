package com.feifei.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断响应
 * ReentrantLock重入锁中断响应方法lockInterruptibly示例
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便生成死锁
     * @param lock
     */
    public IntLock(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            if (lock == 1){
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(1);
        IntLock inLock2 = new IntLock(2);
        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(inLock2);
        t1.start();
        t2.start();
        //主线程休眠1秒
        Thread.sleep(1000);
        //中断t2线程
        t2.interrupt();
    }
}
