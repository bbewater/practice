package com.sz.bewater.practice.interview.juc;

public class MyThreadLocal {
    //ThreadLocal 定义成static final 防止key-ThreadLocal 弱引用类型被回收 导致value无法访问
    // 定义成static final使得始终有强引用类型指向ThreadLocal实例 这样key就不会被gc回收  只需要finally里面remove掉value即可
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) {
//        当一个变量是共享变量  但又想做到每个线程相互隔离 这时候就可以用ThreadLocal来做
//        原理及实现: 每个线程都有一个类型为ThreadLocal.ThreadLocalMap变量 ThreadLocalMap类中有个内部类Entry 他的key为ThreadLocal value为我们要操作的值
//        ThreadLocal可以理解为为了操作线程内部的ThreadLocalMap 而对外暴露的一个工具类
//        ThreadLocal中的set方法 查看源码可知 他会去拿当前线程中的ThreadLocalMap 然后往里面赋值

//        ThreadLocal内存泄露问题:
//        我们知道一个线程池中的核心线程是不会被回收的  而每个线程都有一个强引用指向各自的ThreadLocalMap 而ThreadLocalMap也是用一个强引用指向他的Entry对象
//        当核心线程不会被回收 则Entry也不会被回收 这就导致了内存泄露
//        如何解决?  每次使用完ThreadLocal 后需要调用remove方法 来手动回收掉

//        ThreadLocal的使用场景:
//        1.用于层级之前的传递 当不同层级要使用一个共享变量 而又要考虑线程安全问题 我们就可以用ThreadLocal来做
//        2.Spring中的事务管理 每个事务都会放在一个ThreadLocalMap里面
//        3.SpringMvc中的httpSession、httpServletResponse、httpServletRequest 由于Servlet是单例的 都是放在ThreadLocal里面
        new Thread(() -> {
            try{
                threadLocal.set("线程"+Thread.currentThread().getName()+"的value");
                System.out.println(threadLocal.get());
            }finally {
                threadLocal.remove();
            }


        },"a").start();
        new Thread(() -> {
            try{
                threadLocal.set("线程"+Thread.currentThread().getName()+"的value");
                System.out.println(threadLocal.get());
            }finally {
                threadLocal.remove();
            }
        },"b").start();
        new Thread(() -> {
            try{
                threadLocal.set("线程"+Thread.currentThread().getName()+"的value");
                System.out.println(threadLocal.get());
            }finally {
                threadLocal.remove();
            }
        },"c").start();
        new Thread(() -> {
            try{
                threadLocal.set("线程"+Thread.currentThread().getName()+"的value");
                System.out.println(threadLocal.get());
            }finally {
                threadLocal.remove();
            }
        },"d").start();


    }
}
