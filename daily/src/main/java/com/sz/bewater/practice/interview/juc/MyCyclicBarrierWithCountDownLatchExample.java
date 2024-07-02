package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyCyclicBarrierWithCountDownLatchExample {
//    CycleBarrier 和 CountDownLatch 确实在某些方面有相似性，它们都能用于协调多个线程的执行状态。
//相似之处在于，两者都是为了让多个线程在达到特定条件后，共同执行后续的操作。
//然而，它们也存在显著的不同。
//正如您提到的，CycleBarrier 具有回调的特性。这意味着每个线程在达到中间状态时，可以执行特定的回调逻辑。比如说，在一个多线程计算任务中，每个线程完成一部分计算到达中间状态后，可以通过回调更新计算进度的展示。
//另外，CycleBarrier 支持循环使用。这使得它在需要多次重复类似的线程协调场景中非常有用。例如，在一个反复进行的多阶段任务中，每次阶段结束都可以使用同一个 CycleBarrier 来协调线程。
//相比之下，CountDownLatch 没有回调的概念。多个线程只是单纯地等待计数减为 0 ，然后继续执行。比如在一个并发下载任务中，多个线程负责下载不同部分，当所有下载完成（CountDownLatch 计数减为 0 ），才开始进行文件的整合操作。

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,() -> {
            System.out.println("开始组队玩英雄联盟");
        });

        new Thread(new Player(cyclicBarrier),"奇亚娜").start();
        new Thread(new Player(cyclicBarrier),"诡术妖姬").start();
        new Thread(new Player(cyclicBarrier),"不祥之刃").start();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        },"疾风剑豪").start();


//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"奇亚娜").start();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"诡术妖姬").start();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
//                countDownLatch.countDown();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"不祥之刃").start();
//        try {
//            countDownLatch.await();
//            System.out.println("开始组队玩英雄联盟");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


    }

    static class Player implements Runnable{
        final CyclicBarrier cyclicBarrier;


        public Player(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始下载英雄联盟");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"英雄联盟下载完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
