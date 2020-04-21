package com.feifei;

/**
 * 内部类测试
 * @author xuxiangfei
 * @date 2020/4/2
 */
public class Outer {

     static class Inner {
        public static void foo() {
            new Inner();
        }

        public void bar() {
            new Inner();
        }
    }

    public static void main(String[] args) {
        new Inner();
    }
}
