package com.sz.bewater.practice.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MyLockSupport {
    //用生产者消费者模型  应用LockSupport 来实现线程间的通信

//    LockSupport、CountDownLatch 和 Semaphore 都不是基于 AbstractQueuedSynchronizer (AQS) 的，它们是 java.util.concurrent (JUC) 包下提供的简单工具类，用于实现基本的线程间通信和同步
//    都利用了类似许可的机制来实现线程同步
    private static Thread consumerThread;   //消费者线程
    private static final CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            System.out.println("生产者正在生产");
            try {
                TimeUnit.SECONDS.sleep(2);  //模拟生产者生产业务
                System.out.println("生产者生产完毕 准备唤醒消费者");
                LockSupport.unpark(consumerThread);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });



        consumerThread = new Thread(() -> {
            System.out.println("消费者正在等待生产者生产");
            LockSupport.park(); //消费者阻塞自己 等待被生产者唤醒
            System.out.println("消费者已被唤醒,准备消费");

        });

        producerThread.start();
        consumerThread.start();

//        CountDownLatch也可以做  但是做不到精准唤醒哪一个线程   CountDownLatch的应用场景是一批任务做完了 才可以做下一个 适用于协调多个线程在某个点上同步

//        new Thread(() -> {
//            System.out.println("生产者正在生产");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                latch.countDown();
//                System.out.println("生产者生产完毕 准备唤醒消费者");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }).start();
//
//
//        new Thread(() -> {
//            System.out.println("消费者正在等待生产者生产");
//            try {
//                latch.await();
//                System.out.println("消费者已被唤醒,准备消费");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }).start();



    }
}
