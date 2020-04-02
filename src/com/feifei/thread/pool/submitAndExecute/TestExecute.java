package com.feifei.thread.pool.submitAndExecute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池execute方法，该方法在接口executor中定义
 * 该方法提交到线程池的任务，若线程执行时发生异常会被线程池吃掉，无法抛到外面
 * 可以通过设置线程的UnCaughtExceptionHandler属性，自定义异常处理策略
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class TestExecute {

    //不设置UnCaughtExceptionHandler时
    //public static void main(String[] args) throws InterruptedException {
    //    ExecutorService executorService = Executors.newFixedThreadPool(10);
    //    executorService.execute(() -> {
    //        int i = 7/0;
    //        System.out.println(i);
    //    });
    //    Thread.sleep(3000);
    //    System.out.println("主线程已提交任务，正在执行");
    //    System.out.println(executorService.toString());
    //    executorService.shutdown();
    //
    //}


    //设置UnCaughtExceptionHandler时
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        try {
            executorService.execute(new Thread(new MyTaskThread()));
        } catch (Exception e) {
            System.out.println("进入异常了");
            e.printStackTrace();
        }
        executorService.shutdown();

    }
}
