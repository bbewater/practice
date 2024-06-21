package com.sz.bewater.practice.interview.basic.factory.simpleFactory;

public class Test {

    public static void main(String[] args) {
        MySimpleFactory factory = new MySimpleFactory();
        Product pr = factory.createProduct("A");
        System.out.println(pr);


    }
}
