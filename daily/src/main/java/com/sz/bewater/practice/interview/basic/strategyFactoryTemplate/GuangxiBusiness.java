package com.sz.bewater.practice.interview.basic.strategyFactoryTemplate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${provinceCode:0} == 40")
public class GuangxiBusiness extends ProvinceTemplate{

    @Override
    public void specialBusiness() {
        System.out.println("广西特有业务逻辑");
    }

}
