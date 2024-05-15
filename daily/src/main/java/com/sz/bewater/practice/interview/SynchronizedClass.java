package com.sz.bewater.practice.interview;

public class SynchronizedClass {


    public static void main(String[] args) {
//        javac SynchronizedClass.java
//        javap -v SynchronizedClass.class
//        ObjectMonitor: owner(获取锁的线程)    cxq entryList(阻塞队列)  recursions(可重入锁 重入次数)
//        cmpxchg 多核还需要lock指令 保证原子性 站在java角度 UnSafe类compareAndSwapInt方法  底层还是c++中的cmpxchg搭配lock(多核)

        synchronized (SynchronizedClass.class){

            System.out.println("hello");
        }



    }
}
