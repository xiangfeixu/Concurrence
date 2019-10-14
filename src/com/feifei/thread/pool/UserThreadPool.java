package com.feifei.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author xuxiangfei
 * @date 2019/9/19
 */
public class UserThreadPool {

    public static void main(String[] args) {
        //定义列队容量为2，为了更快的拒绝任务
        LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue(2);

        UserThreadFactory userThreadFactory1 = new UserThreadFactory("第一机房");

        UserThreadFactory userThreadFactory2 = new UserThreadFactory("第一机房");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS, blockingQueue, userThreadFactory1,new UserRejectedHandler());
        for (int i = 0;i < 20;i++) {
            threadPoolExecutor.execute(new Task());
        }

    }
}
