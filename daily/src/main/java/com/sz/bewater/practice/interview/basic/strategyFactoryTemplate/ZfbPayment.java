package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.stereotype.Component;

@Component
public class ZfbPayment extends CommonPayTemplate{
//public class ZfbPayment implements PaymentStrategy{


    @Override
    public void pay() {
        zfbSpecial();
        System.out.println("支付宝支付");
    }

    @Override
    public void zfbSpecial() {
        System.out.println("支付宝特有逻辑");
    }
}
