package com.feifei.juc.blockingqueue.delayqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 缓存类
 * 主要包括put 和 get 方法
 * @author xuxiangfei
 * @date 2020/3/30
 */
public class Cache<K, V> {

    /**
     * 缓存对象map
     */
    private ConcurrentHashMap<K, V> cacheObjMap = new ConcurrentHashMap<>();

    /**
     * 延时阻塞队列
     */
    private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<>();

    /**
     * 守护线程
     */
    private Thread daemonThread;

    public Cache() {
        Runnable deamonTask  = new Runnable() {
            @Override
            public void run() {
                daemonCheck();
            }

        };
        daemonThread = new Thread(deamonTask);
        daemonThread.setDaemon(true);
        daemonThread.setName("Cache Daemon");
        daemonThread.start();
    }

    /**
     * 检查是否守护进程
     */
    private void daemonCheck() {
        System.out.println("cache service start..");
        for (;;) {
            try {
                DelayItem<Pair<K, V>> delayItem = q.take();
                if (delayItem != null) {
                    //超时对象处理
                    Pair<K, V> item = delayItem.getItem();
                    cacheObjMap.remove(item.first, item.second);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("cache service end..");
    }

    /**
     * 向缓存中存放新键值对
     * @param key 键
     * @param value 值
     * @param time 超时时间
     * @param unit 时间单位
     */
    public void put (K key, V value, long time, TimeUnit unit) {
        V oldValue = cacheObjMap.put(key, value);
        if (oldValue != null) {
            q.remove(key);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
        q.put(new DelayItem<>(new Pair<>(key, value), nanoTime));
    }

    /**
     * 从缓存中换区值
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        return cacheObjMap.get(key);
    }

    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, String> cache = new Cache<>();
        cache.put(1, "aaa", 3, TimeUnit.SECONDS);
        Thread.sleep(2000);
        System.out.println(cache.get(1));
        Thread.sleep(2000);
        System.out.println(cache.get(1));
    }

}