package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;

public class MyJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "运行结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "thread1");


        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();     //join可以理解是将调用方线程加入到被调用方  这里就是thread2加入到thread1 所以thread2阻塞住 直到thread1运行结束 join前面那个线程先运行
                System.out.println(Thread.currentThread().getName() + "运行结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "thread2");
        thread2.start();    //让thread2先启动
        thread1.start();



    }
}
