package com.feifei.juc.ThreadPoolDemo;

import com.feifei.juc.ThreadPoolDemo.recursive.MyRecursiveAction;
import com.feifei.juc.ThreadPoolDemo.recursive.MyRecursiveTask;

import java.util.concurrent.ForkJoinPool;

/**
 * ForkJoinPool线程池demo
 * @author xuxiangfei
 * @date 2020/3/29
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        //创建了一个并行级别为 4 的 ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建一个没有返回值的任务
        //MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        //ForkJoinPool 执行任务
        //forkJoinPool.invoke(myRecursiveAction);

        //创建一个有返回值的任务
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        Long invoke = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("myRecursiveTask result = " + invoke);
    }
}
