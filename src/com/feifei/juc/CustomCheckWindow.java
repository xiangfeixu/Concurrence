package com.feifei.juc;

import java.util.concurrent.Semaphore;

/**
 * 乘客检查窗口（Semaphore信号量示例）
 * 场景：海关检查有三个窗口，有五个人排队检查，最前面的人会被优先安排到空闲的服务窗口
 * 有可疑人员需要更多的时间查验信息，但是不影响其他窗口的安检速度
 * 如果把信号量Semaphore的许可证permits设置为1个，就变成了互斥锁，5个人串行通过一个通道
 * @author xuxiangfei
 * @date 2019/9/17
 */
public class CustomCheckWindow {
    private static class SecurityCheckThread extends Thread{
        private int seq;
        private  Semaphore semaphore;

        private SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("NO." + seq + "乘客正在查验中。。。。");
                if (seq % 2 == 0) {
                    Thread.sleep(2000);
                    System.out.println("NO." + seq + "乘客身份可疑，禁止出国。。。。");
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("NO." + seq + "乘客检查结束。。");
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);
        for(int i = 0; i < 6; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }
}
