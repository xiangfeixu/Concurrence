package com.feifei.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray原子性整型数组
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class AtomicIntegerArrayDemo {

    private static  AtomicIntegerArray array = new AtomicIntegerArray(10);

    private static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                array.getAndIncrement(k % array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i< 10; i++) {
            threads[i] = new Thread(new AddThread());
        }
        for (int i = 0; i< 10; i++) {
            threads[i].start();
        }
        for (int i = 0; i< 10; i++) {
            threads[i].join();
        }
        System.out.println(array);
    }

}
