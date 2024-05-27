package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 以AQS方式 实现自定义锁
 */

public class MyCustomLock {
    private static final Sync sync = new Sync();


    public static class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)) {
                setExclusiveOwnerThread(Thread.currentThread());    //设置持有锁的线程为当前线程
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) throw new IllegalMonitorStateException();  //没有持有锁 则会报错
            setExclusiveOwnerThread(null);  //释放锁
            setState(0);    //设置状态为0
            return true;

        }


    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public static void main(String[] args) {
        MyCustomLock lock = new MyCustomLock();



        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);      //保证线程1先拿到锁
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();    //线程2尝试加锁 等待线程1释放锁
            System.out.println(Thread.currentThread().getName()+"获取锁");
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放锁");

        },"thread2").start();


        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获取锁");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放锁");

        },"thread1").start();








    }
}
