package com.feifei.juc.blockingqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue同步队列示范
 * SynchronousQueue 直接使用 CAS 实现线程的安全访问。
 * 它的内部其实没有容器，如果有线程put元素到队列中，暂时没有其他线程来消费这个元素的话，当前线程会阻塞直到有线程来消费
 * @author xuxiangfei
 * @date 2020/3/29
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Thread putThread = new Thread(() -> {
            System.out.println("put Thread start!");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put Thread end!");
        });


        Thread takeThread = new Thread(() -> {
            System.out.println("take Thread start!");
            try {
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take Thread end!");
        });

        putThread.start();
        //这一秒间隔后putThread线程才会执行结束
        Thread.sleep(1000);
        takeThread.start();
    }
}
