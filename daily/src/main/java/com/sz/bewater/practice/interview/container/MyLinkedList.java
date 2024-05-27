package com.sz.bewater.practice.interview.container;

import java.util.LinkedList;
import java.util.Queue;

public class MyLinkedList {


    public static void main(String[] args) {


        LinkedList<Object> linkedList = new LinkedList<>();
//        除了实现了List接口还实现了Deque 双向链表 注意 linkedList的查找顺序不是一定劣与ArrayList 他查找首个元素 和尾部元素还是比较快的
        linkedList.addLast("q");
        linkedList.addFirst("1");   //等价于add()
        linkedList.add("x");
        linkedList.forEach(System.out::println);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        Queue queue = new LinkedList();
//        Object remove = queue.remove();
        Object poll = queue.poll();
        System.out.println(poll);
//        remove和poll都会移除队列中第一个元素 并返回  但是当没有时remove会报错：NoSuchElementException  而poll不会 返回null
    }
}
