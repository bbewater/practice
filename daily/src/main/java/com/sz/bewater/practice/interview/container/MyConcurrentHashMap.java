package com.sz.bewater.practice.interview.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap {

    public static void main(String[] args) {
        //扩容源码

        //为啥有了红黑树 还保留了一个双向链表（next prev）
//            static final class TreeNode<K,V> extends Node<K,V> {
//        TreeNode<K,V> parent;  // red-black tree links    //红黑树父节点
//        TreeNode<K,V> left;   //红黑树左节点
//        TreeNode<K,V> right;  //红黑树右节点
//        TreeNode<K,V> prev;    // needed to unlink next upon deletion  Node中还有个next和这里的prev构成了双向链表
//        boolean red;  //true代表红  false代表黑     插入删除会涉及旋转和变色
//
//        TreeNode(int hash, K key, V val, Node<K,V> next,
//                 TreeNode<K,V> parent) {
//            super(hash, key, val, next);
//            this.parent = parent;
//        }
        // 1.保证读效率(写的时候 读不能阻塞(写数据的时候 树的结构可能会发生变化 这时候不能从树上读 但是也不能阻塞住等写操作完成 可以退而求其次去双向链表上读) 去双向链表上去读)
        // 2.扩容(红黑树的扩容 依赖这个双向链表 使扩容成本降低)
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        System.out.println(map.size());
        System.out.println(map.mappingCount());
        //merge 对于原始 k,v 进行操作 假如 k 不存在也不会报错 而是会以结果 put 进去一个新的 kv
        map.merge("1", "3", (oldValue, newValue) -> oldValue + newValue); //1:13
        map.merge("3", "3", (oldValue, newValue) -> oldValue + newValue);   //3:3
        map.forEach((k,v) -> System.out.println(k + ":" + v));
    }
}
