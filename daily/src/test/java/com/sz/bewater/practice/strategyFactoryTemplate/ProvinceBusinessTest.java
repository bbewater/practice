package com.sz.bewater.practice.strategyFactoryTemplate;

import com.sz.bewater.practice.interview.basic.strategyFactoryTemplate.ProvinceTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvinceBusinessTest {
    @Autowired
    private ProvinceTemplate template;

    @Test
    public void test(){
        template.specialBusiness();
    }


}
