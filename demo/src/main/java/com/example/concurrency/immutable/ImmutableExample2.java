package com.example.concurrency.immutable;

import com.example.concurrency.annotation.ThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * 演示：不可变对象 : Collections.unmodifiableXXX(Object o)
 * - Collections.unmodifiableXXX(Object o)返回了一个XXX类型对应的包装类
 * - 把对应的修改和增加操作改成抛出异常 UnsupportedOperationException
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 11:19.
 */
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //抛出异常 UnsupportedOperationException
        map.put(7, 8);
    }

}
