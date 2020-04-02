package com.feifei.synchronous;

/**
 * synchronized关键字错误示范
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class BadSynchronizedDemo {


    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + "I am methodA!");
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + "I am methodB!");
    }
}
