package com.sz.bewater.practice.interview.cg.singleton;

public class DclLazySingleton {

    private static DclLazySingleton instance;

    private DclLazySingleton() {
    }

    public static DclLazySingleton newInstance(){
        if (instance == null){
            synchronized (DclLazySingleton.class){
                if (instance == null){
                    instance = new DclLazySingleton();
                }
            }
        }
        return  instance;
    }
}
