package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {
    //CountDownLatch 用于 等待一批线程 处理完  在处理剩下业务逻辑

    private static final CountDownLatch latch = new CountDownLatch(5);


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < latch.getCount(); i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"正在干活");
                latch.countDown();
            },"线程"+(i+1)).start();
        }

        latch.await();
        System.out.println("5个线程处理完,开始处理主线程业务");

    }
}
