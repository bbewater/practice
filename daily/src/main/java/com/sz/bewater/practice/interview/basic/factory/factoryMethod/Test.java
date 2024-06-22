package com.sz.bewater.practice.interview.basic.factory.factoryMethod;

import com.sz.bewater.practice.interview.basic.factory.simpleFactory.Product;

public class Test {

    public static void main(String[] args) {
//        AbstractFactory factory = new ProductAFactory();
        Factory factory = new ProductBFactory();
        Product product = factory.create();
        product.call();


    }
}
