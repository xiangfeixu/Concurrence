package com.feifei.volatiletest;

/**
 * volatile 可见的，易变的。该关键字修饰的变量在多线程访问时，是直接在主内存中读取。该关键字修饰的变量禁止进行指令重拍。
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class VolatileTest {

    public static void main(String[] args) {
        final Counter counter = new Counter();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.inc();
                }
            }).start();
        }
        System.out.println(counter);
    }

    static public class Counter {
        private volatile int count = 0;

        public void inc() {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }

        @Override
        public String toString() {
            return "[count=" + count + "]";
        }
    }
}
