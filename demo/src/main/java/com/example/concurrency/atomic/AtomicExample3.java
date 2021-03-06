package com.example.concurrency.atomic;

import com.example.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * 演示【线程安全】的写法
 *  - 使用了 LongAdder
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/26 18:07.
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {
    //线程数(请求总数)
    public static int clientTotal = 5000;

    //允许并发的线程数
    public static int threadTotal = 200;

    //计数值
    public static LongAdder count = new LongAdder();


    private static void add() {
        count.increment();
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //使用信号量控制并发总量，达到允许的并发数之后回阻塞之后的操作等待释放
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                //计数值减一
                countDownLatch.countDown();
            });
        }
//等待所有计数的线程执行完毕

        countDownLatch.await();
        executorService.shutdown();
        log.info("count {}",count);


    }
}
