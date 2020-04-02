package com.feifei.juc.blockingqueue.delayqueue;

/**
 * 定义实体类
 * 有成对的属性
 * @author xuxiangfei
 * @date 2020/3/30
 */
public class Pair<K, V> {


    public K first;

    public V second;

    public Pair() {
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}
