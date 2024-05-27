package com.sz.bewater.practice.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class MyInterruptLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static Thread thread2;

    //lock相较于synchronized，lock可以中断获取锁(等待获取锁的时候被中断)，而synchronized不行
//    lock.interrupt();  效果相当于lock.tryLock(timeout)  一定时间获取不到 我就不获取了  避免线程长时间阻塞 可以去处理别的任务


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();   //表示可被中断的获取锁
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                TimeUnit.SECONDS.sleep(2);  //模拟线程工作
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                LockSupport.unpark(thread2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }, "thread1");

        thread2 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);   //确保线程1先获取锁
                lock.lockInterruptibly();   //表示可被中断的获取锁
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                TimeUnit.SECONDS.sleep(2);  //模拟线程工作
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "线程被中断");
                try {
                    LockSupport.park();
                    lock.lockInterruptibly();   //表示可被中断的获取锁
                    System.out.println(Thread.currentThread().getName() + "重新获取了锁");
                    TimeUnit.SECONDS.sleep(2);  //模拟线程工作
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

//                被中断后 可以去干别的任务
//                doOtherWork();
//                throw new RuntimeException(e);
            }

        }, "thread2");
        thread1.start();
        thread2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500); //确保线程2处于等待锁的状态 为下面中断线程2做准备
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread2.interrupt();


    }
}
