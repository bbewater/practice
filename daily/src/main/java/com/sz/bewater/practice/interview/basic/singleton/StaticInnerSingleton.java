package com.sz.bewater.practice.interview.basic.singleton;
//静态内部类实现延迟加载
public class StaticInnerSingleton {
    private StaticInnerSingleton() {
    }

    static class StaticHolder{
//        当外部类 StaticInnerSingleton 被加载时，并不会立即加载静态内部类 StaticHolder。
//只有在第一次调用 StaticInnerSingleton.newInstance() 方法时，才会触发静态内部类 SingletonHolder 的加载和初始化。
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton newInstance(){
        return StaticHolder.instance;
    }

}
