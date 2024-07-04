package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MyVolatile {
//    private static Boolean flag = false;  //JIT java即时编译器的存在将while(!flag) 优化成了while(true) 因为while循环了很多次 JIT优化器认为可能是while(true) 可通过修改vm参数禁用掉JIT(-Xint 不推荐 得不偿失)  虽然这里thread2也能感受到flag的变化 但是这种并不可靠
    private static volatile Boolean flag = false;     //通过volatile修饰共享变量  保证了可见性 及禁止指令重排序 及禁止JIT对代码进行优化
    private static Thread thread1;
    private static Thread thread2;
    private static Thread thread3;


    public static void main(String[] args) {
        thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
            LockSupport.unpark(thread2);
        }, "thread1");


        thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "------flag is " + flag);
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "------flag is " + flag);
        }, "thread2");


        thread3 = new Thread(() -> {
            int i = 0;
            while (!flag) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + "------flag is " + flag);
        }, "thread3");

        thread1.start();
        thread2.start();
        thread3.start();

    }



}
