package com.feifei.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**CyclicBarrier循环栅栏
 * 高级的CountDownLatch，可以多次使用，并制定完成后触发的动作action
 * 适用于成批执行某项任务的场景，比如机场乘车由现场人员统一管理，每次进来三辆车，等三辆车的人都坐满了，再换三辆新车进来
 * @author xuxiangfei
 * @date 2019/1/7
 */
public class CyclicBarrierDemo {
    public static class Solider implements Runnable {
        private String soliderName;
        private final CyclicBarrier cyclic;

        public Solider(String soliderName, CyclicBarrier cyclic) {
            this.soliderName = soliderName;
            this.cyclic = cyclic;
        }

        @Override
        public void run() {
            try {
                //等待士兵到齐(阻塞当前线程，当数目达到屏障预设的大小时，执行屏障任务，然后继续执行当前任务)
                cyclic.await();
                doWork();
                //等待所有士兵完成工作
                cyclic.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt(10)*1000));
                System.out.println(soliderName+":任务完成！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令 ：【士兵"+N+"个完成任务】");
            } else {
                System.out.println("司令 ：【士兵"+N+"个集合完毕】");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoliders = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(allSoliders.length, new BarrierRun(flag, N));

        //设置屏障点，执行方法
        System.out.println("集合队伍！");
        for (int i = 0;i < N;i++){
            System.out.println("士兵"+i+"：前来报道");
            allSoliders[i] = new Thread(new Solider("士兵" + i, cyclicBarrier));
            allSoliders[i].start();
        }
    }
}
