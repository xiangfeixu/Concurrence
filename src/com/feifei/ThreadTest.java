package com.feifei;

/**
 * 线程start()测试
 * @author xuxiangfei
 * @date 2020/4/2
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
