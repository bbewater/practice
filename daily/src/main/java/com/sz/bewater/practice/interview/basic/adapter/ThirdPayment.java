package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.stereotype.Component;

/**
 * Project: practice
 * Description: 第三方支付方式
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
public class ThirdPayment {

    void thirdPay(Double dollar,Double tax){
        System.out.println("third pay dollar: "+(dollar+tax));
    }
}
