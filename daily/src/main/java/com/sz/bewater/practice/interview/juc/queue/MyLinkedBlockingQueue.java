package com.sz.bewater.practice.interview.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyLinkedBlockingQueue {
//    底层为链表的可有界可无界的阻塞队列
    //不传容量则为无界的 传则跟ArrayBlockingQueue一样是有界的阻塞队列
    private static final BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    linkedBlockingQueue.put(i);
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
                    Integer item = linkedBlockingQueue.take();
                    System.out.println("consumer is consume item:"+(item+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        },"consumer").start();





    }

}
