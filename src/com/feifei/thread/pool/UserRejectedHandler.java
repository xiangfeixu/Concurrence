package com.feifei.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自义定线程拒绝策略
 * @author xuxiangfei
 * @date 2019/9/19
 */
public class UserRejectedHandler implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejucted." + executor.toString());
    }
}
