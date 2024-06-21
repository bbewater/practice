package com.sz.bewater.practice.interview.basic.factory.factoryMethod;

public class Test {

    public static void main(String[] args) {
        CommonFactory factory = new ProductFactory("A");
        System.out.println(factory.createProduct());


    }
}
