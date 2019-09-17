package com.feifei.juc.ThreadPoolDemo;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略demo
 * @author xuxiangfei
 * @date 2019/1/24
 */
public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + "Thread ID: " + Thread.currentThread().getId());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r +" is discard");
                    }
                });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(myTask);
        }
    }
}
