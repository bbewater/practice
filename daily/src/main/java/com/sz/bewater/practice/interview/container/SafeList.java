package com.sz.bewater.practice.interview.container;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class SafeList {

//    除了在方法内部定义ArrayList、LinkedList  定义为局部变量 可以避免线程安全问题
//    也可以用Collections.synchronizedList(new ArrayList())  Collections.synchronizedList(new LinkedList()) 包装一下


    public static void main(String[] args) {
        List<ArrayList<Object>> list1 = Collections.singletonList(new ArrayList<>());
        List<Object> list2 = Collections.synchronizedList(new LinkedList<>());

        CopyOnWriteArrayList<Integer> list3 = new CopyOnWriteArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        Iterator<Integer> iterator = list3.iterator(); //生成 list3快照 后续或迭代过程中对 list3的修改不影响
        list3.add(4);
        while(iterator.hasNext()){
            System.out.println(iterator.next());    //不包含 4 CopyOnWriteList 迭代器弱一致性
        }
    }
}
