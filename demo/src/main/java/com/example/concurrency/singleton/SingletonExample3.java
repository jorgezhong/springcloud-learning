package com.example.concurrency.singleton;

import com.example.concurrency.annotation.NotRecommend;
import com.example.concurrency.annotation.ThreadSafe;

/**
 * 演示：单例
 *  - 懒汉式 ： 单例实例在【第一次使用时】才进行创建
 *  - 代码24到27行是不安全的，多线成情况下，私有构造方法会被访问两次
 *      - 可加锁来进行控制，但是不推荐这种写发
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:45.
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {
    }

    //单例的对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法获取单例对象
    public static synchronized SingletonExample3 getInstance() {
        if (instance != null) {
            return instance;
        }
        return new SingletonExample3();
    }

}
