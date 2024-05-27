package com.sz.bewater.practice.interview.juc;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCyclicBarrier {
//    cyclicBarrier强调可重用性(所有线程可同步多次 每同步一次执行一次回调)与回调  countdownLatch比较简单 所有线程同步一次就够了然后执行后续

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
//        每当所有线程都调用 cyclicBarrier.await() 并通过屏障时，回调函数都会被调用一次。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() ->{
            System.out.println("所有学生完成阶段"+atomicInteger.getAndIncrement()+"学习");     //CyclicBarrier 支持回调 这也是和countDownLatch也不同点 countDownLatch没有回调这种说法
        });

        for (int i = 0; i < 3; i++) {
            new Thread(new Study(cyclicBarrier),"学生"+(i+1)).start();
        }



    }





    static class Study implements Runnable{
        private final CyclicBarrier cyclicBarrier;

        public Study(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+"正在进行阶段"+i+"学习");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"完成阶段"+i+"学习");
                    System.out.println(Thread.currentThread().getName()+"等待其他学生完成阶段"+i+"学习");
                    cyclicBarrier.await();  //for循环里调用cyclicBarrier.await() 说明cyclicBarrier支持复用 不像countDownLatch countDown变成0就没了
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }



        }
    }
}
