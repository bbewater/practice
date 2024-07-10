package com.sz.bewater.practice.interview.basic.singleton;
//饿汉模式 提前创建对象
public class HangerSingleton {
//    饿汉模式无现场安全问题   类的初始化（加载-链接（验证-准备-解析）-初始化）是jvm来保证线程安全的   类的静态变量的赋值是在类的初始化阶段完成的（准备、初始化）  所以也是线程安全的
//    虽然没有线程安全问题 但是对象没有使用就进行实例化了  造成了资源浪费
    private static final HangerSingleton instance = new HangerSingleton();


    private HangerSingleton() { //构造方法私有化 防止外面直接new对象 应该对外暴露一个newInstance来创建单例对象
    }

    public static HangerSingleton newInstance(){
        return instance;
    }


}
