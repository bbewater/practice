package com.sz.bewater.practice.basic;

import com.sz.bewater.practice.interview.basic.adapter.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@SpringBootTest
public class AdapterTest {

//    @Autowired
//    private PayAdapter payAdapter;
//    @Autowired
//    private T3CallCarAdapter t3CallCarAdapter;
//    @Autowired
//    private DiDiCalCarAdapter diDiCalCarAdapter;
    @Autowired
    private CallCarFactoryBean callCarFactoryBean;

    @Test
    public void testThirdPay() throws Exception {
//        payAdapter.pay(new BigDecimal(127.11));
//        t3CallCarAdapter.callCar("bewater","优公馆","苏州北站",12L,0.5d);
//        diDiCalCarAdapter.callCar("bewater","优公馆","苏州北站",12L,0.5d);
        CallCarService callCarService = callCarFactoryBean.getObject();
        callCarService.callCar("bewater","优公馆","苏州北站",12L,0.5d);
    }
}
