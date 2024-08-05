package com.sz.bewater.practice.strategyFactoryTemplate;

import com.sz.bewater.practice.interview.basic.strategyFactoryTemplate.CommonPayTemplate;
import com.sz.bewater.practice.interview.basic.strategyFactoryTemplate.PaymentFactory;
import com.sz.bewater.practice.interview.basic.strategyFactoryTemplate.PaymentStrategy;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class strategyFactoryTemplateTest {
    @Autowired
    PaymentFactory factory;

    @Test
    public void test(){
//        PaymentStrategy strategy = factory.getPaymentStrategy(2);         //策略模式+简单工厂模式(代替策略模式的上下文)
//        strategy.pay();
        CommonPayTemplate template = factory.getPaymentStrategy(2);    //策略模式+简单工厂模式(代替策略模式的上下文)+模板方法模式(可以定义一些公用逻辑和一些子类特有逻辑)
        template.commonPay();
    }
}
