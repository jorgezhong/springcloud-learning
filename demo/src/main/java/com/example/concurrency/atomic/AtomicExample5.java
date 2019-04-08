package com.example.concurrency.atomic;

import com.example.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 演示
 * - 使用 AtomicIntegerFieldUpdater更改指定类的Integer类型的成员变量
 * - 注意：该字段必须用volatile修饰,且不能是static修饰的
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/26 18:07.
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    @Getter
    private volatile int count = 100;
    //计数值
    public static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success1 {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success2 {}", example5.getCount());
        } else {
            log.info("update failed {}", example5.getCount());
        }


    }
}
