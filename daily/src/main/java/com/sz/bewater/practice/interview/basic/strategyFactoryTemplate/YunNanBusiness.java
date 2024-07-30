package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${provinceCode:0} == 50")
public class YunNanBusiness extends ProvinceTemplate{
    @Override
    public void specialBusiness() {
        templateMethod();
        System.out.println("云南特有业务逻辑");
    }

    @Override
    public void ynSpe() {
        System.out.println("云南插入的一些业务逻辑");
    }
}
