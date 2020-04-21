package com.feifei.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * distinct方法测试
 * @author xuxiangfei
 * @date 2020/4/20
 */
public class DistinctDemo {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(6, 4, 8, 7, 3, 8);
        List<Integer> collect = integers.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}
