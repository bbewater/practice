package com.sz.bewater.practice.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MySemaphore {

    private static final Semaphore accquires = new Semaphore(3);    //3个许可  最多允许三个线程同时访问(拿到许可)


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    accquires.acquire();
                    System.out.println(Thread.currentThread().getName()+"获取到许可");
                    TimeUnit.MILLISECONDS.sleep(2000);   //模拟业务处理
                    accquires.release();
                    System.out.println(Thread.currentThread().getName()+"释放许可");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"thread"+i).start();
        }
    }
}
