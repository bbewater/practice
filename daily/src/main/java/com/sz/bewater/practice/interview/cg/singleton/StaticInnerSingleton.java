package com.sz.bewater.practice.interview.cg.singleton;

public class StaticInnerSingleton {

    private StaticInnerSingleton() {
    }

    static class StaticHolder {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton newInstance(){
        return StaticHolder.instance;
    }
}
