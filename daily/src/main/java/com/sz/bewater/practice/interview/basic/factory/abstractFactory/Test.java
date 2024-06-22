package com.sz.bewater.practice.interview.basic.factory.abstractFactory;

public class Test {

    public static void main(String[] args) {
        AbstractFactory miFactory = new MiFactory();
        Car miCar = miFactory.createCar();
        miCar.calling();
        Phone miPhone = miFactory.createPhone();
        miPhone.calling();
        AbstractFactory huaweiFactory = new HuaWeiFactory();
        Phone hwPhone = huaweiFactory.createPhone();
        hwPhone.calling();
        Car hwCar = huaweiFactory.createCar();
        hwCar.calling();
    }

}
