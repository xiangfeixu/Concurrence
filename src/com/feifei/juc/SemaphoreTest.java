package com.feifei.juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量，可以用来限制多个线程对一个方法的并发方法的数量
 * 相当于通道上预留的闸机口，最多允许多少个乘客同时通过
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class SemaphoreTest {

    /**
     * 定义一个信号量，该类内部维持了多个线程锁，可以阻塞多个线程，释放多个线程，
     * 线程的阻塞和释放是通过 permit 概念来实现的
     * 线程通过 semaphore.acquire()方法获取 permit，如果当前 semaphore 有 permit 则分配给该线程，
     * 如果没有则阻塞该线程直到 semaphore
     * 调用 release（）方法释放 permit。
     * 构造函数中参数：permit（允许） 个数，
     */
    private static Semaphore semaphore = new Semaphore(5, true);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

    private static void test() {
        try {
            //申请一个请求
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "进来了！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "走了！");
        semaphore.release();
    }


}
