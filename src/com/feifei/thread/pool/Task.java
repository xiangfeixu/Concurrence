package com.feifei.thread.pool;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程执行任务类
 * @author xuxiangfei
 * @date 2019/9/19
 */
public class Task implements Runnable{

    private static AtomicLong longAdder = new AtomicLong(0L);

    /**
     * 任务方法
     */
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+ "正在执行任务："+"runner_" + longAdder.getAndIncrement());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
