package com.feifei.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream中map方法可以用来处理单个元素
 * @author xuxiangfei
 * @date 2020/4/20
 */
public class MapDemo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("ni", "hao", "a");
        String collect = strings.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(collect);
    }
}
