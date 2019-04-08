package com.example.concurrency.singleton;

import com.example.concurrency.annotation.ThreadUnsafe;

/**
 * 演示：单例
 *  - 懒汉式 ： 单例实例在【第一次使用时】才进行创建
 *  - 代码24到27行是不安全的，多线成情况下，私有构造方法会被访问两次
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:45.
 */
@ThreadUnsafe
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1() {
    }

    //单例的对象
    private static SingletonExample1 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample1 getInstance() {
        if (instance != null) {
            return instance;
        }
        return new SingletonExample1();
    }

}
