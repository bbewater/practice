package com.sz.bewater.practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/5/1
 */
public class PrintTest {
    public static Thread thread1;
    public static Thread thread2;
    public static final ReentrantLock lock = new ReentrantLock();
    public static final Condition condition = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
         thread1 = new Thread(() -> {
            for (int i = 0; i <=200 ; i=i+2) {
                System.out.println(Thread.currentThread().getName()+": "+i);
                LockSupport.unpark(thread2);
                if (i == 200){
                    break;
                }
                LockSupport.park();
            }



        },"thread1");
         thread2 = new Thread(() -> {
             for (int i = 1; i < 200 ; i=i+2) {
                 LockSupport.park();
                 System.out.println(Thread.currentThread().getName()+": "+i);
                 LockSupport.unpark(thread1);
                 if (i == 199){
                     break;
                 }

             }
        },"thread2");

//         thread1 = new Thread(() -> {
//             for (int i = 0; i <=200 ; i=i+2) {
//                 try {
//                     lock.lock();
//                     System.out.println(Thread.currentThread().getName()+": "+i);
//                     condition.signal();
//                     if (i == 200){
//                         break;
//                     }
//                     condition.await();
//                 } catch (InterruptedException e) {
//                     throw new RuntimeException(e);
//                 }finally {
//                     lock.unlock();
//                 }
//             }
//
//         },"thread1");
//
//         thread2 = new Thread(() -> {
//             for (int i = 1; i < 200 ; i=i+2) {
//                 try {
//                     lock.lock();
//                     System.out.println(Thread.currentThread().getName()+": "+i);
//                     condition.signal();
//                     condition.await();
//                     if (i == 199){
//                         break;
//                     }
//                 } catch (InterruptedException e) {
//                     throw new RuntimeException(e);
//                 }finally {
//                     lock.unlock();
//                 }
//
//
//             }
//        },"thread2");


        thread1.start();
        thread2.start();

    }
}
