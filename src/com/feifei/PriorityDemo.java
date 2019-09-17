package com.feifei;

/**
 * 线程优先级priority测试类
 * @author xuxiangfei
 * @date 2018/12/29
 */
public class PriorityDemo {
    public static class HighPriority extends Thread {
        static int count = 0;
        @Override
        public void run() {
            while(true) {
                synchronized (HighPriority.class){
                    count++;
                    if (count > 1000000) {
                        System.out.println("HighPriority complete!");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (LowPriority.class){
                    count++;
                    if (count >1000000) {
                        System.out.println("LowPriority complete!");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority = new HighPriority();
        LowPriority lowPriority = new LowPriority();
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.NORM_PRIORITY);
        highPriority.start();
        lowPriority.start();
    }
}
