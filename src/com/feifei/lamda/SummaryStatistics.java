package com.feifei.lamda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * 摘要示例
 * @author xuxiangfei
 * @date 2020/4/20
 */
public class SummaryStatistics {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(12, 47, 3, 56, 21, 33);
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("sum = " + intSummaryStatistics.getSum());
        System.out.println("max = " + intSummaryStatistics.getMax());
        System.out.println("min = " + intSummaryStatistics.getMin());
        System.out.println("avg = " + intSummaryStatistics.getAverage());
    }
}
