package com.feifei.juc.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义拓展线程池
 * 自己实现beforeExecute afterExecute方法追踪线程池的信息
 * @author xuxiangfei
 * @date 2019/2/19
 */
public class ExtThreadPool {
    public static class MyTask implements Runnable{
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行" + "Thread ID:" + Thread.currentThread().getId()
                + ",Task Name:" + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行："+ ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成："+ ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出！");
            }
        };

        for (int i = 0;i < 5;i++) {
            es.execute(new MyTask("Task-Name-"+i));
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
