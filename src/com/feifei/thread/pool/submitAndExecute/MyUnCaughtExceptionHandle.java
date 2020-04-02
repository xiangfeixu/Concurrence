package com.feifei.thread.pool.submitAndExecute;

/**
 * 自定义线程未处理的异常处理策略
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class MyUnCaughtExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}
