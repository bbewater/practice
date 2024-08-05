package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public CommonPayTemplate getPaymentStrategy(int payType){
        switch (payType){
            case 1:
//                return new ZfbPayment();
                return applicationContext.getBean(ZfbPayment.class);
            case 2:
//                return new WxPayment();
                return applicationContext.getBean(WxPayment.class);
            case 3:
//                return new CreditPayment();
                return applicationContext.getBean(CreditPayment.class);
            default:
                //可拓展新的支付方式
                throw new RuntimeException("请选择合法的支付方式");
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
