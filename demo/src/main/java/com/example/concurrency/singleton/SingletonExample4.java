package com.example.concurrency.singleton;

import com.example.concurrency.annotation.ThreadUnsafe;

/**
 * 演示：单例 -- 双重同步锁单例模式
 * - 懒汉式 ： 单例实例在【第一次使用时】才进行创建
 * - 懒汉式西安测绘给你不安全，可加锁来进行控制，却不推荐加方法锁
 * - 使用【双重校验机制的同步锁】可规避方法加锁带来的性能消耗
 * - 该类并非线程安全的
 *   在实例化对象的过程中会做以下三件事
 *      1. memory = allocate() 分配内存空间
 *      2. ctorInstance 初始化对象
 *      3. instance = memory 设置instance指向刚分配的内存
 *
 *   但是JVM和CPU优化，发生了指令重排，分配内存和初始化对象没什么必然关系
 *      1. memory = allocate() 分配内存空间
 *      3. instance = memory 设置instance指向刚分配的内存
 *      2. ctorInstance 初始化对象
 *
 *   由于指令重排的存在，存在小概率事件：在没有初始化对象的过程就使得【instance != null】
 *   因此，双重校验机制，可能拿到没有进行初始化的对象，再往下调用就会出现问题
 *
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/4/8 9:45.
 */
@ThreadUnsafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {
    }

    //单例的对象
    private static SingletonExample4 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample4 getInstance() {

        if (instance == null) {     //双重校验机制
            synchronized (SingletonExample4.class) {    //同步锁
                if (instance == null) {
                    return new SingletonExample4();
                }
            }
        }
        return instance;
    }

}
