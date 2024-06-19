package com.sz.bewater.practice.interview.basic.proxy.staticProxy;

public class MyStaticTarget implements CommonService{
    @Override
    public void method() {
        System.out.println("这是目标类打印信息");
    }
}
