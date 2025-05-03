package com.sz.bewater.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/5/3
 */
public class ThreadByOrder {
    public static  Thread t1;
    public static  Thread t2;
    public static  Thread t3;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName());
                    LockSupport.unpark(t2);
                    LockSupport.park();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"t1");
        t2 = new Thread(() -> {
            LockSupport.park();
            while(true){
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(t3);
                LockSupport.park();
            }
        },"t2");
        t3 = new Thread(() -> {
            LockSupport.park();
            while(true){
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        },"t3");


        t1.start();
        t2.start();
        t3.start();
    }
}
