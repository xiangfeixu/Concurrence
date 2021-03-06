package com.feifei;

import java.util.ArrayList;

/**
 * ArrayList多线程测试
 * 线程不安全，可用Vector代替
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<>(10);
    private static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i < 1000000;i++) {
                al.add(i);

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
