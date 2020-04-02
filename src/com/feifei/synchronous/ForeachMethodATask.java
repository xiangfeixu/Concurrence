package com.feifei.synchronous;


/**
 * for循环调用A方法任务
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class ForeachMethodATask implements Runnable {

    private BadSynchronizedDemo demo;

    public ForeachMethodATask(BadSynchronizedDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            demo.methodA();
        }
    }
}
