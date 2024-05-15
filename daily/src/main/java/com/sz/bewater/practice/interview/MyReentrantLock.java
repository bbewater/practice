package com.sz.bewater.practice.interview;

public class MyReentrantLock {

    public static void main(String[] args) {
//        1.灵活度比synchronized高很多 tryLock tryLock(long timeout, TimeUnit unit)  基于AQS    java关键字(底层是基于指令ObjectMonitor)
//        2.可实现公平锁
//        3.性能方面差不太多(除非竞争特别激烈 reentrantLock优一点点 但是很少 指的是1.6之后 synchronized做了优化)
//        4.虽然reentrantLock更灵活 但是需要我们自己去释放锁(死锁)  synchronized不需要(jvm帮我们做了)





    }
}
