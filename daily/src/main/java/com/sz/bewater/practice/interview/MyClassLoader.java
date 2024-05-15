package com.sz.bewater.practice.interview;

public class MyClassLoader {

    public static void main(String[] args) {
//        向上检查(加载过则不再加载) 向下委派(往下去加载 直到 加载不到为止 ClassNotFoundException)
//        CustomerClassLoader--->AppClassLoader--->ExtClassLoader--->BootstrapClassLoader  向上
//        责任链机制
//        保证同一个类只加载一次 (性能 安全(不能自定义的类将我系统自带的类给替换掉))


    }
}
