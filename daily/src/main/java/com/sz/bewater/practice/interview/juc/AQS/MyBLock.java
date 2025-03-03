package com.sz.bewater.practice.interview.juc.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Project: practice
 * Description: 依据AQS实现的自定义独占非公平锁
 *
 * @Author: zhoudun
 * @Date: 2024/12/25
 */
public class MyBLock {
    private Sync sync = new Sync();

    public static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {//加锁
            int state = getState();
            if (compareAndSetState(0, arg)) { //第一次获取锁
                setExclusiveOwnerThread(Thread.currentThread());    //将独占锁线程置为当前线程
                return true;
            }
            if (state > 0 && getExclusiveOwnerThread() == Thread.currentThread()) {//可重入
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) { //释放锁
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            //当且仅当 state == 1的时候 可释放锁
            int newState = getState() - arg;
            setState(newState);
            if (newState == 0){
                setExclusiveOwnerThread(null);  //将独占锁线程置为null
                return true;
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively() { //该独占锁是否被自己占有
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    public void lock(){
        sync.acquire(1);
    }

    public void unlock(){
        sync.release(1);
    }


    public static void main(String[] args) {
        MyBLock lock = new MyBLock();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("thread A get Lock");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }).start();


        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("thread B get Lock");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }).start();

    }

}
