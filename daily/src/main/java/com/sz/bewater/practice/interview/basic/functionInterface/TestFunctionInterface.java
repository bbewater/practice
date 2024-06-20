package com.sz.bewater.practice.interview.basic.functionInterface;

import java.util.HashMap;
import java.util.Map;

public class TestFunctionInterface {




    public static void main(String[] args) {
        Map<String,MyFunction> map = new HashMap<>();
        //MyFunction为函数式接口 故可以写成lamda表达式 当函数式接口的调用 来实际执行lamda中的代码
//        Lambda 表达式提供了一种更简洁、更直观的方式来实现函数式接口，使得代码更加简洁和可读
//        这里的test方法 可以看成是函数式接口的实现  实际调用的是test方法
        map.put("1",() -> test());
        map.get("1").method();
//        map.put("1", new MyFunction() {   //匿名内部类也可以做到像lamda表达式那样 简化代码 但是编译后会创建新类 性能也不如lamda表达式
//            @Override
//            public void method() {
//                test();
//            }
//        });
//        map.get("1").method();



    }

    public static void test(){
        System.out.println("调用了函数式接口 相当于调用了lamda表达式 也即调用了test方法");
    }
}
