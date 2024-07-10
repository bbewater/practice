package com.sz.bewater.practice.interview.basic.singleton;
//dcl 加锁前后进行判断  double check  减小锁的粒度提高性能
public class DclLazySingleton {
    private static volatile DclLazySingleton instance;


    private DclLazySingleton() { //构造方法私有化 防止外面直接new对象 应该对外暴露一个newInstance来创建单例对象
    }

    public static DclLazySingleton newInstance(){
        if (instance == null){  //第一次判断 假如不成立就没必要加锁操作了
            synchronized (DclLazySingleton.class){
                if (instance == null){  //第二次判断 是防止加锁前 对象已经被其他线程实例化了
                    instance = new DclLazySingleton();
                }
            }
        }
        return instance;

    }





}
