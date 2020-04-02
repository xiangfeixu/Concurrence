package com.feifei.thread.pool.submitAndExecute;

/**
 * @author xuxiangfei
 * @date 2020/4/1
 */
public class MyTaskThread implements Runnable{

    @Override
    public void run() {
        int i = 7/0;
    }
}
