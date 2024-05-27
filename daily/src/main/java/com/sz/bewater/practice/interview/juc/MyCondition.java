package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCondition {
    //还是利用生产者消费者模型 这次使用Condition来实现线程通信
//    与lockSupport相比  condition需要搭配锁来使用   因为condition本身就由lock创建的   lock.await后 进入条件队列  lock.signal 会将条件队列里唤醒第一个线程 来进入同步队列 等待被cpu调度
//    lockSupport使用比较简单不依赖锁 直接调用的unsafe类中的park和unpark方法  但是condition在线程间更复杂的通信更有优势

//    Condition 与 LockSupport 的区别：
//
    //Condition 需要与锁（如 ReentrantLock）一起使用，因为 Condition 本身是由锁创建的。
    //当线程调用 condition.await() 时，它会进入条件队列并释放锁。condition.signal() 会唤醒条件队列中的第一个线程，使其进入同步队列，等待被 CPU 调度。
    //LockSupport 使用更加简单，不依赖于锁，直接调用 Unsafe 类中的 park 和 unpark 方法来实现线程阻塞和唤醒。
    //使用场景与优势：
    //
    //Condition 在复杂线程通信场景中更有优势。通过多个条件变量，可以实现精细的线程协调和复杂的同步逻辑。
    //LockSupport 更加直接和低级，适用于实现简单的线程阻塞和唤醒机制，不需要额外的锁和条件变量。
    //补充说明
    //Condition 的实现机制：
    //
    //Condition 是 Lock 的扩展，可以有多个条件变量，每个条件变量都有自己的等待队列。(这句是与lockSupport的关键区别  我觉得)
    //await 方法会自动释放锁，进入条件队列，等待 signal 或 signalAll 方法的通知。
    //signal 方法会从条件队列中唤醒一个等待线程，使其进入同步队列。
    //LockSupport 的实现机制：
    //
    //LockSupport 通过 park 方法阻塞线程，unpark 方法唤醒线程。
    //不依赖于锁，可以在任意代码位置使用，非常灵活。
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean productSucc = false; //生产者是否生产完的标识


    public static void main(String[] args) {

        Thread produceThread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("生产者获取到锁 开始生产");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("生产者生产完毕,释放锁,并准备重新唤醒消费线程");
                productSucc = true;
                condition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();  //确保在发送信号后 释放锁
            }
        });


        Thread consumerThread = new Thread(() -> {
            lock.lock();
            System.out.println("消费者获取到锁");
            while (!productSucc) {
                try {
                    System.out.println("生产者未准备好,准备阻塞消费线程,等待生产者生产完毕");
                    condition.await();  //阻塞进条件队列 并释放锁 等待被唤醒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();  //确保消费完 释放锁
                }
            }
            System.out.println("消费者被唤醒,开始消费");

        });


        consumerThread.start(); //消费者线程先启动
        produceThread.start();

    }


}
