package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyCyclicBarrierWithCountDownLatchExample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,() -> {
            System.out.println("开始组队玩英雄联盟");
        });

        new Thread(new Player(cyclicBarrier),"奇亚娜").start();
        new Thread(new Player(cyclicBarrier),"诡术妖姬").start();
        new Thread(new Player(cyclicBarrier),"不祥之刃").start();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        },"疾风剑豪").start();


//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"奇亚娜").start();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"诡术妖姬").start();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"不祥之刃").start();
//        try {
//            countDownLatch.await();
//            System.out.println("开始组队玩英雄联盟");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


    }

    static class Player implements Runnable{
        final CyclicBarrier cyclicBarrier;


        public Player(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
