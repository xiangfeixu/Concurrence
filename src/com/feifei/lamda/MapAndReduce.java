package com.feifei.lamda;

import java.util.Arrays;
import java.util.List;

/**
 * stream map和reduce方法
 * @author xuxiangfei
 * @date 2020/4/20
 */
public class MapAndReduce {


    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(100, 200, 300, 400, 500);
        integers.stream().map(cost -> cost * (1+ 0.12)).forEach(System.out::println);

        Double aDouble = integers.stream().map(cost -> cost + cost * 0.12).reduce((sum, cost) -> sum + cost).get();
        System.out.println(aDouble);
    }
}
