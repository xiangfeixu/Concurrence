package com.feifei;

/**
 * 错误加锁使用示例
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class BadLockOnIntegerDemo implements Runnable{
    //Integer 不可变类
    public static Integer i = 0;
    static BadLockOnIntegerDemo instance = new BadLockOnIntegerDemo();

    @Override
    public void run() {
        for (int j = 0;j < 100000;j++){
            synchronized (i) {
                //每次执行都会产生一个新的Integer对象
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new BadLockOnIntegerDemo());
        Thread t2 = new Thread(new BadLockOnIntegerDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i 最后的结果为："+i);
    }
}
