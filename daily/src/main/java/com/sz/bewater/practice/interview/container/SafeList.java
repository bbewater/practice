package com.sz.bewater.practice.interview.container;

import java.util.*;

public class SafeList {

//    除了在方法内部定义ArrayList、LinkedList  定义为局部变量 可以避免线程安全问题
//    也可以用Collections.synchronizedList(new ArrayList())  Collections.synchronizedList(new LinkedList()) 包装一下


    public static void main(String[] args) {
        List<ArrayList<Object>> list1 = Collections.singletonList(new ArrayList<>());
        List<Object> list2 = Collections.synchronizedList(new LinkedList<>());
    }
}
