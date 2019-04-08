package com.example.concurrency.singleton;

import com.example.concurrency.annotation.Recommend;
import com.example.concurrency.annotation.ThreadSafe;

/**
 * 演示：枚举模式的单例
 * - 推荐这种写法，枚举中的单例创建由JVM保证西安测绘给你安全
 *
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 10:32.
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        /**
         * 枚举实例
         */
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证该方法绝对只被调用一次
        Singleton() {
            this.singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return this.singleton;
        }
    }

}
