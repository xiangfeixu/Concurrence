package com.feifei;

/**
 * 守护线程Daemnon测试类
 * 守护线程，即后台线程，系统的守护者，做一些的系统性的工作，当系统的工作线程即用户线程结束时，
 * 守护线程就自动结束了
 * @author xuxiangfei
 * @date 2019/1/2
 */
public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            System.out.println("I'm alive");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        //设置守护线程必须在线程启动前，否则会出现异常java.lang.IllegalThreadStateException
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
