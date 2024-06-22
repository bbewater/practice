package com.sz.bewater.practice.interview.basic.factory.factoryMethod;

import com.sz.bewater.practice.interview.basic.factory.simpleFactory.Product;
import com.sz.bewater.practice.interview.basic.factory.simpleFactory.ProductB;

public class ProductBFactory implements Factory {
    @Override
    public Product create() {
        return new ProductB();
    }
}
