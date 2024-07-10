package com.sz.bewater.practice.interview.basic.singleton;

public class MyTest {
    public static void main(String[] args) {
        LazySingleton lazySingleton = LazySingleton.newInstance();  //懒汉模式 有线程安全问题
        HangerSingleton hangerSingleton = HangerSingleton.newInstance();    //饿汉模式  无线程安全问题 但是有资源损耗
        DclLazySingleton dclLazySingleton = DclLazySingleton.newInstance(); //DCL方式的懒汉模式 无线程安全问题  但是存在锁和volatile 一定程度上有性能损耗 且编码较复杂
        StaticInnerSingleton staticInnerSingleton = StaticInnerSingleton.newInstance(); //静态内部类的方式 静态内部类实现延迟加载  类加载机制JVM保证线程安全问题


    }
}
