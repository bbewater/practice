package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.stereotype.Component;

@Component
public class WxPayment extends CommonPayTemplate{
//public class WxPayment implements PaymentStrategy{
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
