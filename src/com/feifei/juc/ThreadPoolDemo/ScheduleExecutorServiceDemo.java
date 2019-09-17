package com.feifei.juc.ThreadPoolDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 调度任务线程池
 * @author xuxiangfei
 * @date 2019/1/24
 */
public class ScheduleExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() ->{
                try {
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis()/1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
