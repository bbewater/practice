package com.sz.bewater.practice.interview.basic.factory.factoryMethod;

import com.sz.bewater.practice.interview.basic.factory.simpleFactory.Product;
import com.sz.bewater.practice.interview.basic.factory.simpleFactory.ProductA;

public class ProductAFactory implements Factory {
    @Override
    public Product create() {
        return new ProductA();
    }
}
