package com.sz.bewater.practice.interview.basic.factory.abstractFactory;

public class MiFactory implements AbstractFactory{
//    小米的工厂  即可生产小米手机 也可以生产小米汽车  小米汽车和小米手机 不同的对象类型 但是从属于一家公司(产品族相同)
    @Override
    public Phone createPhone() {
        return new MiPhone();
    }

    @Override
    public Car createCar() {
        return new MiCar();
    }
}
