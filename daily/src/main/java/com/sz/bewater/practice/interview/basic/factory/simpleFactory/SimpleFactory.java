package com.sz.bewater.practice.interview.basic.factory.simpleFactory;

public class SimpleFactory {


    public static Product createProduct(String type){
        if (type.equals("A")){
            return new ProductA();
        }
        if (type.equals("B")){
            return new ProductB();
        }
        return null;
    }
}
