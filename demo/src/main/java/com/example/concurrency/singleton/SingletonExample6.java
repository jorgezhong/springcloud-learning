package com.example.concurrency.singleton;

import com.example.concurrency.annotation.ThreadSafe;

/**
 * 演示：单例
 *  - 饿汉式 ： 单例实例在【类装载时】才进行创建
 *  - 饿汉模式是线程安全的
 *  - 缺点（不足）：
 *      - 私有构造不可有过多的处理，如果太多处理会导致类加载较慢，可能引起性能问题
 *      - 该类在实际过程中必须被使用，不适用则造成浪费
 *
 *  - 饿汉式除了再静态域中进行创建，还可以再静态代码块进行创建
 *  - 注意：静态代码块必须再静态域的后面（如果静态代码块中使用到静态域的话）
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:45.
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6() {
    }

    //单例的对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法获取单例对象
    public static SingletonExample6 getInstance() {
            return instance;
    }

}
