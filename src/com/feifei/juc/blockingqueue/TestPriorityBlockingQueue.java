package com.feifei.juc.blockingqueue;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 测试PriorityBlockingQueue
 * @author xuxiangfei
 * @date 2020/3/29
 */
public class TestPriorityBlockingQueue {
    private static PriorityBlockingQueue<User> queue  = new PriorityBlockingQueue<>();

    public static void main(String[] args) {
        queue.add(new User(2, "222"));
        queue.add(new User(6, "666"));
        queue.add(new User(4, "444"));
        queue.add(new User(3, "333"));
        for (User user : queue) {
            try {
                System.out.println(queue.take().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    static class User implements Comparable<User>{
        private int age;
        private String name;

        User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return this.age > o.age ? 1 : -1;
        }
    }
}
