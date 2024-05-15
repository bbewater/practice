package com.sz.bewater.practice.interview;

import java.util.concurrent.locks.ReentrantLock;

public class MyAQS {


    public static void main(String[] args) {
//        AbstractQueuedSynchronizer 抽象类
        //1.private volatile int state;  0:无线程持有 1、2、3(>1即代表有线程持有)  对标synchronized中的recursions
//        2.Node head(Node) tail(Node)  阻塞队列
// 3.ConditionObject(实现了Condition接口)  里面是单向链表   起到了类比于synchronzied中的wait/notify/notifyAll的作用
//  当调用了signal() await() 线程就会被封装成node对象被放到这个单向链表中(尾部) 等待被唤醒



    }
}
