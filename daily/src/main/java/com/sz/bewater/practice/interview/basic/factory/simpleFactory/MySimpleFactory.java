package com.sz.bewater.practice.interview.basic.factory.simpleFactory;

public class MySimpleFactory {

    public Product createProduct(String productType){
        if (productType.equals("A")){
            return new ProductA();
        }
        return new ProductB();
    }

}
