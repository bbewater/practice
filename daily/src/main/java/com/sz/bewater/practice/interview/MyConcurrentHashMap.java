package com.sz.bewater.practice.interview;

import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap {

    public static void main(String[] args) {
        //扩容源码

        //为啥有了红黑树 还保留了一个双向链表（next prev）
        // 1.保证读效率(写的时候 读不能阻塞(写数据的时候 树的结构可能会发生变化 这时候不能从树上读 但是也不能阻塞住等写操作完成 可以退而求其次去双向链表上读) 去双向链表上去读)
        // 2.扩容(红黑树的扩容 依赖这个双向链表 使扩容成本降低)

    }
}
