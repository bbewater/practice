package com.sz.bewater.practice.interview;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MyHashMap {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //容量指的是hashMap中数组的大小（初始值为16） 2的幂 Node<K,V>[] table; 也就是hash桶   大小指的是Node元素的总和
//        扩容时重新计算新数组的下标(resize()) 要么新下标=旧下标  要么 新下标=就下标+旧数组的容量

//        1.8之前  数组(table)+链表(Node)   1.8之后  数组(table)+链表(Node)+红黑树(TreeNode)
//        扩容:
//        1.8之前 1.创建新数组 2.遍历旧数组每个位置上的链表的每个元素  3.重新计算每个元素在新数组上的下标 4,搬运元素到新数组  5.将table值指向新数组
//        1.8之后  1.创建新数组 2.遍历旧数组每个位置上的链表或者红黑树 3.假如是链表则和之前的没差
//                  假如说是红黑树  则会先计算树上的每个元素在新数组上的位置  当新数组位置上的元素超过8 则会生成一个红黑树 然后将根节点添加到新数组对应的位置  假如说没有超过8  则生成一个链表 然后将头节点添加到对应位置上  4.搬运完成后将table指向新数组

//        HashMap线程不安全  高并发情况成环问题  多个线程 可能将Node的next指针指向不同的Node 导致成环    使用ConcurrentHashMap(table+Node+TreeNode+TreeBin(不存储元素 而是管理TreeNode的容器  并提供并发控制锁机制 来保证线程安全))
//        TreeBin  TreeNode<K,V> root：红黑树的根节点。  volatile TreeNode<K,V> first：红黑树中第一个节点的引用。 volatile Thread waiter：等待锁的线程。 volatile int lockState：锁状态，用于实现细粒度的并发控制。




    }
}
