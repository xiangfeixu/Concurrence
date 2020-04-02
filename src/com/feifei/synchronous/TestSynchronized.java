package com.feifei.synchronous;

/**
 * 测试多线程访问同一个对象不同的同步方法
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class TestSynchronized {

    public static void main(String[] args) {
        BadSynchronizedDemo demo = new BadSynchronizedDemo();
        ForeachMethodATask task1 = new ForeachMethodATask(demo);
        ForeachMethodBTask task2 = new ForeachMethodBTask(demo);
        new Thread(task1).start();
        new Thread(task2).start();
    }
}
