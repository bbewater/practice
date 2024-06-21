package com.sz.bewater.practice.interview.basic.factory.factoryMethod;

public class ProductFactory implements CommonFactory{
    private String type;

    public ProductFactory(String type) {
        this.type = type;
    }

    @Override
    public Object createProduct() {
        if (type.equals("A")){
            return new ProductA();
        }
        return new ProductB();
    }
}
