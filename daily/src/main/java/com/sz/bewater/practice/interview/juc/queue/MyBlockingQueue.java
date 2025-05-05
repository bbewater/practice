package com.sz.bewater.practice.interview.juc.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project: practice
 * Description: 使用 linkedList+synchronized/lock 实现阻塞队列
 *
 * @Author: zhoudun
 * @Date: 2025/5/6
 */
public class MyBlockingQueue<T> {

    private LinkedList<T> queue = new LinkedList<>();
    private volatile Integer capacity;

    public ReentrantLock lock = new ReentrantLock();
    public Condition full = lock.newCondition();
    public Condition empty = lock.newCondition();


    public MyBlockingQueue(Integer capacity) {
        this.capacity = capacity;
    }

//    public void put(T o) throws InterruptedException {
//        synchronized (this) {
//            while (queue.size() >= capacity) {
//                this.wait();
//            }
//            queue.addLast(o);
//            this.notifyAll();
//        }
//    }

//    public Object take() throws InterruptedException {
//        synchronized (this) {
//            while (queue.isEmpty()) {
//                this.wait();
//            }
//
//            Object o = queue.removeFirst();
//            this.notifyAll();
//            return o;
//
//        }
//    }


    public void put(T o) throws InterruptedException {
        try{
            lock.lock();
            while (queue.size() >= capacity){
                full.await();
            }
            queue.addLast(o);
            empty.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public T take() throws InterruptedException {
        T t = null;
        try {
            lock.lock();
            while (queue.isEmpty()){
                empty.await();
            }
            t = queue.removeFirst();
            full.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }


}
