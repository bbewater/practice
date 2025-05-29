package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${provinceCode:0} == 30")
public class SichuanBusiness extends ProvinceTemplate{

    @Override
    public void specialBusiness() {
        System.out.println("四川特有逻辑");
    }

}
