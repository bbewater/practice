package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.stereotype.Component;

@Component
public class CreditPayment extends CommonPayTemplate{
//public class CreditPayment implements PaymentStrategy{
    @Override
    public void pay() {
        System.out.println("信用卡支付");
    }


}
