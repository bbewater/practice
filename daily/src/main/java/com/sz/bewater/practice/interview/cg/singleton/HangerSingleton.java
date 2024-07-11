package com.sz.bewater.practice.interview.cg.singleton;

public class HangerSingleton {
    private static final HangerSingleton instance = new HangerSingleton();

    private HangerSingleton() {
    }

    public static HangerSingleton newInstance(){
        return instance;
    }
}
