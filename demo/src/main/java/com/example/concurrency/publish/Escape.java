package com.example.concurrency.publish;

import com.example.concurrency.annotation.Recommend;
import com.example.concurrency.annotation.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 演示：对象溢出
 *  - 当一个对象在构造完成之前就被其他线程使用（看见）
 *  - 本例中在Escape构造完成之前，就已经在InnerClass中被使用了
 *
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:37.
 */
@Slf4j
@ThreadUnsafe
@Recommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }


    public static void main(String[] args) {
        new Escape();
    }
}
