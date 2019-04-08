package com.example.concurrency.immutable;

import com.example.concurrency.annotation.ThreadUnsafe;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 演示 不可变对象 : final修饰类、方法、成员变量
 *  - 除了使用final还可以使用
 *  - Collections.unmodifiableXXX(Object o)
 *  - Guava提供的一系列不可变引用类型 ImmutableXXX类
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 11:19.
 */
@ThreadUnsafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //不可变，引用引用类型不可改变引用
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();

        //引用类型可继续使用
        map.put(7, 8);
    }

    //final可以修饰入参，用于标识入参不可变
    private void test(final int a) {
        System.out.println("a = " + a);
    }

}
