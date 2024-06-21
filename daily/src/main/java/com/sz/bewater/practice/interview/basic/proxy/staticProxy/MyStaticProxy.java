package com.sz.bewater.practice.interview.basic.proxy.staticProxy;

public class MyStaticProxy implements CommonService{
    private MyStaticTarget target;

    public MyStaticProxy(MyStaticTarget target) {
        this.target = target;
    }

    @Override
    public void method() {
        System.out.println("这是静态代理类打印前");
        target.method();
        System.out.println("这是静态代理类打印后");
    }

    public static void main(String[] args) {
        CommonService staticProxy = new MyStaticProxy(new MyStaticTarget());
        staticProxy.method();



    }
}
