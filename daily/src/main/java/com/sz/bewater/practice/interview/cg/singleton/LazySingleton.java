package com.sz.bewater.practice.interview.cg.singleton;

public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton newInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}
