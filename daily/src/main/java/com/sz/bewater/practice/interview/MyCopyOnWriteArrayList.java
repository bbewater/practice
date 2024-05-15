package com.sz.bewater.practice.interview;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class MyCopyOnWriteArrayList {
//保证多线程情况下 并发写的线程安全

    public static void main(String[] args) throws InterruptedException {
//        源码如下:
//            public boolean add(E e) {
//        final ReentrantLock lock = this.lock;
//        lock.lock();
//        try {
//            Object[] elements = getArray();
//            int len = elements.length;
//            Object[] newElements = Arrays.copyOf(elements, len + 1);
//            newElements[len] = e;
//            setArray(newElements);
//            return true;
//        } finally {
//            lock.unlock();
//        }
//    }

//        ArrayList线程不安全(add方法线程不安全)  多线程写 不加锁处理的话 出现写覆盖 无法保证线程安全
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        CountDownLatch countDownLatch = new CountDownLatch(10000);
//        for (int i = 0; i < 10000; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                arrayList.add(finalI);
//                countDownLatch.countDown();
//            }).start();
//        }
//        countDownLatch.await();
//        new Thread(() -> {
//            System.out.println(arrayList.size());   //<10000 出现了写覆盖
//        }).start();

//        CopyOnWriteArrayList在写操作的时候会复制一个新的数组 写操作在这个新数组上操作(会加锁 保证写操作的线程安全) 操作完将新数组赋值到原数组上
//        写的时候假如有读操作存在 就从旧数组上读值  不会阻塞读操作  提高了读的效率 适用于写少读多(对数据的实时性要求不是很高 因为读的旧数组)的情况
//        弊端: 1.会消耗较多的内存(因为会复制一个新数组)   2.读的不一定是最新的值(从旧数组读值)
//        为什么适合读多写少(读远超于写)  写比较耗内存 且写互斥 大量写显然不合适  但是读是无锁的 所以读很高效  所以读多写少适合用这个
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(() -> {
                copyOnWriteArrayList.add(finalI);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        new Thread(() -> {
            System.out.println(copyOnWriteArrayList.size());    //==10000 写线程安全  不会出现写覆盖
        }).start();
    }
}
