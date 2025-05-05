package com.sz.bewater.practice.interview.juc.queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project: practice
 * Description: 双锁实现阻塞队列 put+take 在队列正常时可以并发执行 双锁+condition+共享变量
 *
 * @Author: zhoudun
 * @Date: 2025/5/6
 */
public class MyBlockingQueueConVer {
    private final int capacity; // 队列容量上限
    private final LinkedList<Object> queue = new LinkedList<>(); // 队列本体
    private final AtomicInteger count = new AtomicInteger(0); // 当前元素个数，原子操作避免竞争

    // 写锁，控制 put 操作
    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition(); // 写锁对应条件：队列未满

    // 读锁，控制 take 操作
    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition(); // 读锁对应条件：队列非空

    public MyBlockingQueueConVer(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 放入元素（阻塞）
     */
    public void put(Object o) throws InterruptedException {
        putLock.lock(); // 进入写锁
        try {
            while (count.get() >= capacity) {
                notFull.await(); // 队列满了就阻塞等待
            }

            queue.addLast(o); // 添加元素
            int c = count.getAndIncrement(); // 元素数量 +1

            // 如果添加后队列仍未满，则通知其他生产者也可以添加
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock(); // 释放写锁
        }

        // 添加成功后，通知消费者可以消费了
        if (count.get() > 0) {
            signalNotEmpty();
        }
    }

    /**
     * 取出元素（阻塞）
     */
    public Object take() throws InterruptedException {
        Object result;

        takeLock.lock(); // 进入读锁
        try {
            while (count.get() == 0) {
                notEmpty.await(); // 队列空了就阻塞等待
            }

            result = queue.removeFirst(); // 移除头部元素
            int c = count.getAndDecrement(); // 元素数量 -1

            // 如果取出后还有元素，通知其他消费者也可以取
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock(); // 释放读锁
        }

        // 取出成功后，通知生产者可以继续放元素了
        if (count.get() < capacity) {
            signalNotFull();
        }

        return result;
    }

    /**
     * 通知消费者可以继续消费了
     */
    private void signalNotEmpty() {
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 通知生产者可以继续生产了
     */
    private void signalNotFull() {
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }
}
