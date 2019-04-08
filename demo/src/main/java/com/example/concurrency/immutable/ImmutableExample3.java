package com.example.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * 演示：不可变对象 : Guava提供的不可变引用类型 ImmutableXXX
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 11:44.
 */
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 =
            ImmutableMap
            .<Integer, Integer>builder()
            .put(1, 2)
            .put(3, 4)
            .build();


    public static void main(String[] args) {
        //对不可变对象进行修改
        //抛出异常 UnsupportedOperationException
        list.add(1);
        set.add(1);
        map.put(5, 6);
        map2.put(7, 8);
    }
}
