package com.example.concurrency.atomic;

import com.example.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示
 *  - 使用 AtomicReference
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/26 18:07.
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {


    //计数值
    public static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] args) {

        //达到期望值更改
        count.compareAndSet(0, 2);  // 0 -> 2
        count.compareAndSet(0, 1);  // no
        count.compareAndSet(1, 3);  // no
        count.compareAndSet(2, 4);  //2 -> 4
        count.compareAndSet(3, 5);  // no

        log.info(MessageFormat.format("count [{0}]", count));

    }
}
