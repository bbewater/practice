package com.sz.bewater.practice.interview.basic.singleton;
//懒汉模式  需要时再创建对象
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {       //构造方法私有化 防止外面直接new对象 应该对外暴露一个newInstance来创建单例对象
    }

    public static LazySingleton newInstance(){
        if (instance == null){      //非线程安全  可考虑在方法上加synchronized锁
            instance = new LazySingleton();
        }
        return instance;
    }



}
