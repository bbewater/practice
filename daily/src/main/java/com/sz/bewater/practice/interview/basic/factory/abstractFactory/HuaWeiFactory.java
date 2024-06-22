package com.sz.bewater.practice.interview.basic.factory.abstractFactory;

public class HuaWeiFactory implements AbstractFactory{
    //    华为的工厂  即可生产华为手机 也可以生产问界汽车  问界汽车和华为手机 不同的对象类型 但是从属于一家公司(产品族相同)
    @Override
    public Phone createPhone() {
        return new HuaWeiphone();
    }

    @Override
    public Car createCar() {
        return new WenJieCar();
    }
}
