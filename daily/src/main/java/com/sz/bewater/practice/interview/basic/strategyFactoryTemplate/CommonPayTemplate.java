package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.stereotype.Component;

public abstract class CommonPayTemplate implements PaymentStrategy{
    //模板方法通常被定义为final，防止子类修改模板方法的结构。
    //抽象类中 将方法定义为final或者private 都是不希望子类去重写的
    public final void commonPay(){
        check();
        pay();
        // TODO: 2024/7/29  后续步骤多了可拓展 如添加日志

    }


    //公用逻辑
    private void check(){
        System.out.println("校验操作");
    }


    //子类直接决定要不要重写  可实现子类特有逻辑
    public void zfbSpecial(){

    }


}
