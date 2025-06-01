package com.sz.bewater.practice;

import java.util.concurrent.locks.LockSupport;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/5/30
 */
public class PrintBy3ThreadOrder {
    private static Thread t1;
    private static Thread t2;
    private static Thread t3;


    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i=i+3) {
                System.out.println(Thread.currentThread().getName()+"---"+i);
                if (i==100){
                    break;
                }
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        },"t1");
        t2 = new Thread(() -> {
            for (int i = 2; i <= 100; i=i+3) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+"---"+i);
                LockSupport.unpark(t3);
                if (i==98){
                    break;
                }

            }
        },"t2");
        t3 = new Thread(() -> {
            for (int i = 3; i <= 100; i=i+3) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+"---"+i);
                LockSupport.unpark(t1);
                if (i==99){
                    break;
                }
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();



    }


}
