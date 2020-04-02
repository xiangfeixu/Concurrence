package com.feifei.thread.pool.submitAndExecute;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂，重写线程生成方式
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class HandlerThreadFactory implements ThreadFactory {

    private MyUnCaughtExceptionHandle unCaughtExceptionHandle = new MyUnCaughtExceptionHandle();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(unCaughtExceptionHandle);
        return null;
    }
}
