package com.sz.bewater.practice.interview;

import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock {
    private static final ReentrantLock mainLock = new ReentrantLock(true);  //可实现公平锁


    public static void businessMethod(){
        System.out.println("当前线程为:"+Thread.currentThread().getName()+"获取了锁,正在执行业务逻辑");
    }

    public static void main(String[] args) {
//        1.灵活度比synchronized高很多 tryLock tryLock(long timeout, TimeUnit unit)  基于AQS    java关键字(底层是基于指令ObjectMonitor)
//        2.可实现公平锁 synchronized非公平
//          公平锁：在加锁期间(lock()) 先看队列中有没有正在等待的线程 有就进队列排队
//          非公平锁：在加锁期间(lock()) 先去竞争一下 竞争不到 就进队列排队
//          注意 不管是公平和非公平 加锁失败 都会进队列的尾部排队 公平与非公平是针对加锁期间而言的
//        3.性能方面差不太多(除非竞争特别激烈 reentrantLock优一点点 但是很少 指的是1.6之后 synchronized做了优化)
//        4.虽然reentrantLock更灵活 但是需要我们自己去释放锁(死锁)  synchronized不需要(jvm帮我们做了)
//        5.synchronized不可中断  reentrantLock可中断(tryLock设置超时时间  interrupt()方法???)
//        6.synchronized锁信息放在对象头(owner代表获取锁的线程 recursions代表重入次数)  ReentrantLock基于AQS 用state(int)记录锁状态 >1代表锁被占用也记录着重入次数
//        7.synchronized有锁升级的过程(1.6之后) 无锁->偏向锁(自旋一次)-》轻量级锁(不会阻塞线程)-》重量级锁(重量级锁 代表会阻塞住线程 有用户态到内核态的一个切换 有性能开销)
//        synchronized在1.6以后不再单纯理解为重量级锁了(锁升级)  而reentrantLock可以看作是重量级锁
//        reentrantLock在高竞争情况下 性能稍好(更完善的锁竞争机制) 而在低竞争下synchronized更好(jvm级别的锁)




        new Thread(()->{
            mainLock.lock();
            System.out.println(mainLock.isLocked()+"------");   //锁是否被占用
            System.out.println(mainLock.isHeldByCurrentThread()+"------");  //锁是否被当前线程占用
            businessMethod();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mainLock.unlock();
        },"线程1").start();



        new Thread(()->{
            mainLock.lock();
            System.out.println(mainLock.isLocked()+"------");
            System.out.println(mainLock.isHeldByCurrentThread()+"------");
            businessMethod();
            mainLock.unlock();
        },"线程2").start();

    }
}
