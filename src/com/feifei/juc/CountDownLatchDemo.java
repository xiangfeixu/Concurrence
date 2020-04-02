package com.feifei.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 倒计时器CountDownLatch用法示例
 * 多线程协同工作工具类 控制N个线程全部完成.调用await方法阻塞主线程，直到所有子线程执行完毕.
 * @author xuxiangfei
 * @date 2019/1/3
 */
public class CountDownLatchDemo implements Runnable{
    private static CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
    private static CountDownLatch count = new CountDownLatch(10);

    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 10;i++) {
            Future<?> future = executorService.submit(countDownLatchDemo);
//            future.get();
        }
        //等待检查
        count.await();
        //发射火箭
        System.out.println("Fire !!!");
        //关闭线程池
        executorService.shutdown();
    }
}
