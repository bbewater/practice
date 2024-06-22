package com.sz.bewater.practice.interview.basic.factory.simpleFactory;


public class Test {

    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct("A");
        product.call();


    }
}
