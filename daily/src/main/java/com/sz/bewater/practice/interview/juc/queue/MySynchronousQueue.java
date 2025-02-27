package com.sz.bewater.practice.interview.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class MySynchronousQueue {
//    不存储元素的阻塞队列  每生产一个必须得等到消费掉才会生产下一个 每个 put 操作必须等待一个 take 操作，反之亦然。
    private static final BlockingQueue<Integer> synchronousQueue = new SynchronousQueue<>();


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    synchronousQueue.put(i);
                    System.out.println("生产者生产了: "+(i+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"producer").start();

        new Thread(() -> {
            while(true){
                try {
                    Integer take = synchronousQueue.take();
                    System.out.println("消费者消费了:"+(take+1));
                    TimeUnit.MILLISECONDS.sleep(1000);   //模拟消费者消费慢  生产者必须得等消费者消费一条 他才生产一条
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }



        },"consumer").start();





    }

}
