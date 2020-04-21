package com.feifei.reflect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态代理示例
 * @author xuxiangfei
 * @date 2020/4/2
 */
public class DynamicProxyDemo {

    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        System.out.println(list);
        Class<? extends List> clazz = list.getClass();
        List<String> proxyListInstance = (List<String>) Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(), (proxy, method, args1) -> method.invoke(list, args1));
        proxyListInstance.add("test add");
        System.out.println(list);
    }
}
