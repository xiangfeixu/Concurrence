package com.feifei.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock() 获取所对象 成功true，失败false
 * 该类中设计为死锁模式，线程一和线程二分别获取lock1和lock2之后，再尝试去获取另外一把锁
 * 由于线程的休眠0.5秒，最终两个线程都会获取到两把锁执行
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class TimeTry implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TimeTry(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId()+":my job is done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId()+":my job is done");
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeTry timeTry1 = new TimeTry(1);
        TimeTry timeTry2 = new TimeTry(2);
        Thread t1 = new Thread(timeTry1);
        Thread t2 = new Thread(timeTry2);
        t1.start();
        t2.start();
    }
}
