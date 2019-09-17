package com.feifei;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap多线程测试
 * jdk7中HashMap多线程中容易死循环的错误
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class HashMapMultiDemo {
    static Map<String,String> map = new HashMap<>();
    public static class AddThread implements Runnable {
        int start;
        public AddThread(int start){
            this.start = start;
        }
        @Override
        public void run() {
            for (int i = start;i < 100000;i += 2){
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("map size ="+map.size());
    }
}
