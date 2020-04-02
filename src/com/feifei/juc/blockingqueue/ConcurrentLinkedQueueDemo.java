package com.feifei.juc.blockingqueue;

import javax.lang.model.element.NestingKind;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ConcurrentLinkedQueue实例
 * @author xuxiangfei
 * @date 2020/3/30
 */
public class ConcurrentLinkedQueueDemo {

    public static int threadCount = 10;

    public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    /**
     * 入队操作任务类
     */
    static class Offer implements Runnable{

        @Override
        public void run() {
            if (queue.isEmpty()) {
                String ele = new Random().nextInt(Integer.MAX_VALUE) + "";
                queue.offer(ele);
                System.out.println("入队元素为" +  ele);
            }
        }
    }

    /**
     * 入队操作任务类
     */
    static class Poll implements Runnable{

        @Override
        public void run() {
            if (!queue.isEmpty()) {
                String ele = queue.poll();
                System.out.println("出队元素为" +  ele);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int x = 0; x < threadCount; x++) {
            executorService.submit(new Offer());
            executorService.submit(new Poll());
        }
        executorService.shutdown();
    }
}
