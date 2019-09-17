package com.feifei;

/**
 * synchronized关键字使用
 * @author xuxiangfei
 * @date 2018/12/29
 */
class GoodSynchronizedDemo implements Runnable{

    private static  GoodSynchronizedDemo instance = new GoodSynchronizedDemo();

    private static volatile int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++){
            //synchronized关键字 修饰代码块 参数可以是 当前对象this，实例对象，类对象
            synchronized (GoodSynchronizedDemo.class) {
                num += 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new GoodSynchronizedDemo());
        Thread t2 = new Thread(new GoodSynchronizedDemo());
//        Thread t1 = new Thread(instance);
//        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("程序执行时间:"+time+"毫秒,最终的输出结果 num="+num);
    }
}
