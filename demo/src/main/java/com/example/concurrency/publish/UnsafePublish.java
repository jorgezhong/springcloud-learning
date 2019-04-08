package com.example.concurrency.publish;

import com.example.concurrency.annotation.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 演示：
 *  - 不安全发布一个对象，外部线程可能对该对象进行改变
 *
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:34.
 */
@Slf4j
@ThreadUnsafe
public class UnsafePublish {

    private String[] status = {"a", "b", "c"};

    public String[] getStatus(){
        return status;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));

        unsafePublish.getStatus()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStatus()));
    }

}
