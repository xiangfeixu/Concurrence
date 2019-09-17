package com.feifei.juc;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写锁Demo
 * 读读操作可以并发，读写、写写操作串行
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            //线程休眠，模拟读操作
            Thread.sleep(1000);
            System.out.println("读取到value值为"+value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("value值被写为"+index);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleRead(readLock);
                    //一般的重入锁
//                    readWriteLockDemo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
                    //传入reentrantLock作为参数
//                    readWriteLockDemo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //设置18次读操作
        for (int i = 0;i < 18;i++) {
            new Thread(readRunnable).start();
        }
        //设置2次写操作
        for (int j = 18;j < 20;j++) {
            new Thread(writeRunnable).start();
        }
    }
}
