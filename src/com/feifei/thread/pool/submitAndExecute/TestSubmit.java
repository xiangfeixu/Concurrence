package com.feifei.thread.pool.submitAndExecute;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试线程池submit方法，该方法在ExecutorService中定义
 * submit不会抛出异常，线程执行时如果抛出异常，会将异常暂存起来，再调用返回值Future对象的get方法释放出来
 * 调用submit方法会返回一个Future对象
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class TestSubmit {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new Thread(new MyTaskThread()));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
