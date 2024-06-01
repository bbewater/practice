package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCOrderByThread {
//    目的 多线程 交替打印A、B、C
    private static Thread threadA;
    private static Thread threadB;
    private static Thread threadC;
    private static final Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
//            threadA = new Thread(() ->{
//                try {
//                    while(true){
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println("A");
//                        LockSupport.unpark(threadB);
//                        LockSupport.park();
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            threadB = new Thread(() ->{
//                try {
//                    while(true){
//                        LockSupport.park();
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println("B");
//                        LockSupport.unpark(threadC);
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            threadC = new Thread(() ->{
//                try {
//                    while(true){
//                        LockSupport.park();
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println("C");
//                        LockSupport.unpark(threadA);
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            threadA.start();
//            threadB.start();
//            threadC.start();


        new Thread(() -> {
            lock.lock();
            try {
                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"----------A");
                    conditionB.signal();
                    conditionA.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }

        },"threadA").start();

        new Thread(() -> {
            lock.lock();
            try {
                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"--------B");
                    conditionC.signal();
                    conditionB.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }

        },"threadB").start();

        new Thread(() -> {
            lock.lock();
            try {
                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"------C");
                    conditionA.signal();
                    conditionC.await();

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }

        },"threadC").start();









    }
}
