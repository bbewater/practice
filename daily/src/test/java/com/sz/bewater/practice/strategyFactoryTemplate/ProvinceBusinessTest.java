package com.sz.bewater.practice.strategyFactoryTemplate;

import com.sz.bewater.practice.interview.basic.strategyFactoryTemplate.ProvinceTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ProvinceBusinessTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test(){
        ProvinceTemplate template = applicationContext.getBean(ProvinceTemplate.class);
        template.templateMethod();
    }


}
