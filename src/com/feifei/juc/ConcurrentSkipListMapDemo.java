package com.feifei.juc;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap 使用skipList结构实现的“哈希表”，线程安全
 * 跟TreeMap一样，键key可以实现排序，但是TreeMap非线程安全
 *
 * 面是“多个线程同时操作并且遍历 map”的示例
 *  (01) 当 map 是 ConcurrentSkipListMap 对象时，程序能正常运行。
 *  (02) 当 map 是 TreeMap 对象时，程序会产生 ConcurrentModificationException 异常。
 * @author xuxiangfei
 * @date 2020/3/31
 */
public class ConcurrentSkipListMapDemo {

    // TODO: map 是 TreeMap 对象时，程序会出错
    private static Map<String, String> map = new TreeMap<>();

    //private static Map<String, String> map = new ConcurrentSkipListMap<>();

    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                String val = Thread.currentThread().getName() + i;
                map.put(val, "0");
                putALL();
            }
        }
        private void putALL() {
            for (Map.Entry<String, String> next : map.entrySet()) {
                System.out.println("(" + next.getKey() + ", "+ next.getValue() + ")");
            }
        }

    }

    public static void main(String[] args) {
        new MyThread("aaa").start();
        new MyThread("bbb").start();
    }
}
