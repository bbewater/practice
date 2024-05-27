package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyArrayBlockingQueue {
//    底层为数组的有界队列
//    阻塞发生在两个地方 put和take  当阻塞队列已满  put会被阻塞 直到阻塞队列元素减少
//    当阻塞队列为空 take会被阻塞  直到阻塞队列有了新元素
    private static final BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    arrayBlockingQueue.put(i);
                    System.out.println("producer is create item:"+(i+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer").start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);  //模拟消费者启动需要时间  生产者生产假如超过了阻塞队列最大容量 将会阻塞 直到阻塞队列被消费掉一个
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while(true){
                try {
                    Integer item = arrayBlockingQueue.take();
                    System.out.println("consumer is consume item:"+(item+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        },"consumer").start();





    }


}
