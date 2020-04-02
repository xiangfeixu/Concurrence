package com.feifei.juc.blockingqueue.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 延时元素类
 * @author xuxiangfei
 * @date 2020/3/30
 */
public class DelayItem<T> implements Delayed {


    /**
     * 基础的纳秒计时，用来避免包装
     */
    public static final long NANO_ORIGIN = System.nanoTime();

    /**
     * 返回当前时间与基础纳秒计时的偏差
     * @return 当前时间与基础纳秒计时的偏差
     */
    static long now () {
        return System.nanoTime() - NANO_ORIGIN;
    }

    /**
     * 中断调度连接的序列号
     * 用来保证先进先出的顺序
     */
    public static final AtomicLong sequencer = new AtomicLong(0);

    /**
     * Sequence number to break ties FIFO
     */
    private final long sequenceNumber;

    /**
     *  time the task is enabled to execute in nanoTime units
     */
    private final long time;

    /**
     * 元素值
     */
    private final T item;

    /**
     * 有参构造器
     * @param submit 提交值
     * @param timeOut 超时时间
     */
    public DelayItem(T submit, long timeOut) {
        this.time = now() + timeOut;
        this.item = submit;
        this.sequenceNumber = sequencer.getAndIncrement();

    }

    /**
     * 获取元素值
     * @return item
     */
    public T getItem() {
        return item;
    }

    /**
     * 获取延迟值
     * @param unit 时间单位
     * @return 延迟时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - now(), TimeUnit.NANOSECONDS);
    }

    /**
     * 元素比较方法
     * @param other 其他比较对象
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof DelayItem) {
            DelayItem x = (DelayItem) other;
            long diff = time - x.time;
            if (diff > 0){
                return 1;
            } else if (diff < 0) {
                return -1;
            } else if (sequenceNumber < x.sequenceNumber) {
                return -1;
            } else {
                return 1;
            }
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - getDelay(TimeUnit.NANOSECONDS));
        return d == 0 ? 0 : (d > 0 ? 1 : -1);
    }
}
