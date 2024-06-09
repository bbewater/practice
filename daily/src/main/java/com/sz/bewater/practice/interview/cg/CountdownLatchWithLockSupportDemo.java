package com.sz.bewater.practice.interview.cg;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class CountdownLatchWithLockSupportDemo {
    private static final CountDownLatch latch = new CountDownLatch(3);
    private static Thread prepareThread;
    private static Thread cookThread;


    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"正在买菜");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"买完菜了,准备唤醒备菜线程");
                latch.countDown();
                LockSupport.unpark(prepareThread);  //唤醒备菜线程
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"buyThread").start();

        prepareThread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"被唤醒,开始备菜了");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"备完菜了,开始唤醒做菜线程");
                latch.countDown();
                LockSupport.unpark(cookThread);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"prepareThread");

        cookThread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"被唤醒,开始做菜了");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"做好菜了");
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        },"cookThread");

        prepareThread.start();
        cookThread.start();


        try {
            latch.await();
            System.out.println("一切准备就绪,开始吃饭");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
