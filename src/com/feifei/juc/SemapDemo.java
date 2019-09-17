package com.feifei.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量Semaphore
 * 允许多个线程同时访问
 * acquire（）尝试获取一个准入许可，如无法获取知道有线程释放一个许可或当前线程被中断
 * release（）线程访问结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问
 * ..tryAcquire()、acquireUninterruptibly()
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class SemapDemo implements Runnable {
    private final Semaphore sema = new Semaphore(5);

    @Override
    public void run() {
        try {
            sema.acquire();
            //模拟超时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
            sema.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemapDemo semapDemo = new SemapDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0;i < 20;i++) {
            executorService.submit(semapDemo);
        }
    }
}
