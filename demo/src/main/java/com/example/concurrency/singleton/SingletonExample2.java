package com.example.concurrency.singleton;

import com.example.concurrency.annotation.ThreadSafe;

/**
 * 演示：单例
 *  - 饿汉式 ： 单例实例在【类装载时】才进行创建
 *  - 饿汉模式是线程安全的
 *  - 缺点（不足）：
 *      - 私有构造不可有过多的处理，如果太多处理会导致类加载较慢，可能引起性能问题
 *      - 该类在实际过程中必须被使用，不适用则造成浪费
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:45.
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2() {
    }

    //单例的对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法获取单例对象
    public static SingletonExample2 getInstance() {
            return instance;

    }

}
