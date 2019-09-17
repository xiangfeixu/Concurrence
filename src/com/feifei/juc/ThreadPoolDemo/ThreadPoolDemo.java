package com.feifei.juc.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数量线程池
 * @author xuxiangfei
 * @date 2019/1/24
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thread ID: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        MyTask myTask = new MyTask();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(myTask);
        }
    }
}
