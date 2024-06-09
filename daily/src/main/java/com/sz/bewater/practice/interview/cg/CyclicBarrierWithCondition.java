package com.sz.bewater.practice.interview.cg;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrierWithCondition {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();


    public static void main(String[] args) {



        new Thread(() -> {

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"此时饭菜未准备就绪,需等待");
                condition.await();  //此时饭菜未准备就绪 需阻塞在这里
                System.out.println(Thread.currentThread().getName()+"开始吃饭");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        },"eatThread").start();

        CyclicBarrier barrier = new CyclicBarrier(2,() -> {
            lock.lock();    //condition的使用 必须得在持有锁的条件下
            System.out.println("饭和菜都已全部准备好,开始唤醒吃饭线程");
            condition.signal();
            lock.unlock();
        });

        new Thread(new CookRiceTask(barrier),"cookRiceThread").start();
        new Thread(new CookFoodTask(barrier),"cookFoodThread").start();


    }


    static class CookRiceTask implements Runnable {
        private CyclicBarrier barrier;

        public CookRiceTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始准备饭");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"饭已准备好");
                barrier.await();    //做饭线程完成工作 阻塞在此处  等待做菜任务同步至此
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class CookFoodTask implements Runnable {
        private CyclicBarrier barrier;

        public CookFoodTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始准备菜");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"菜已准备好");
                barrier.await();    //做菜线程完成工作 同步在此处 待都准备好 一起提交(可以理解成一起提交)
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
