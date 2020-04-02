package com.feifei.synchronous;

/**
 * for循环调用B方法任务
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class ForeachMethodBTask implements Runnable{

    private BadSynchronizedDemo demo;

    public ForeachMethodBTask(BadSynchronizedDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            demo.methodB();
        }
    }
}
