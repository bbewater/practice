package com.sz.bewater.practice.interview.basic.proxy.jdkDynamicProxy;

import com.sz.bewater.practice.interview.basic.proxy.staticProxy.CommonService;

public class MyDynamicTarget implements CommonService {
    @Override
    public void method() {
        System.out.println("这是第一个目标类打印信息");
    }
}
