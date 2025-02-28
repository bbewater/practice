package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/2/28
 */
public class MyInterruptThread {
    public static Thread t1;
    public static Thread t2;

    public static void main(String[] args) {
        MyInterruptThread interrupt = new MyInterruptThread();
        t1 = new Thread(() -> {
            while (!t1.isInterrupted()){    //while (!Thread.interrupted())
                System.out.println("is running");
            }
            System.out.println("is interrupted");
            //除了不断检查线程中断标志位 还可以在线程处于waitting 、timeWaitting状态下 可被中断的  通过捕获InterruptException来响应中断信号
//            try {
//                synchronized (interrupt) {
//                    System.out.println("get lock");
//                    interrupt.wait(5000);
//                }
//            } catch (InterruptedException e) {
//                System.out.println("is interrupted");
//            }

        });

        t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t1.interrupt(); //打断t1
        });
        t1.start();
        t2.start();


    }


}
