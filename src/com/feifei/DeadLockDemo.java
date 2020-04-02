package com.feifei;

/**
 * 死锁示例
 * 线程间相互占用着对方请求的资源不释放，形成闭环
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class DeadLockDemo implements Runnable{

    private int flag;

    private static Object obj1 = new Object(),obj2 = new Object();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-flag:" + flag);
        if (flag == 1) {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "成功获取obj1锁对象");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + "成功获取obj2锁对象");
                }
            }
        }
        if (flag == 0) {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + "成功获取obj2锁对象");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "成功获取obj1锁对象");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLockDemo deadLock1 = new DeadLockDemo();
        DeadLockDemo deadLock2 = new DeadLockDemo();
        deadLock1.flag = 1;
        deadLock2.flag = 0;
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }
}
