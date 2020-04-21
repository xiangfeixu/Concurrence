package com.feifei.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate测试
 * @author xuxiangfei
 * @date 2020/4/20
 */
public class PredicateTest {


    /**
     * 打印以 J 开头并且长度为4的字符串
     * @param list
     */
    private static void printStr(List<String> list) {
        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLength = (n) -> n.length() == 4;
        list.stream().filter(startWithJ.and(fourLength)).forEach(System.out::println);

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5);
        list.add("J001");
        list.add("J002");
        list.add("J003");
        list.add("J004");
        printStr(list);
    }
}
