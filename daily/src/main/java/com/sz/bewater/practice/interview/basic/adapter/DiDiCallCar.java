package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
public class DiDiCallCar {
    public static final BigDecimal startingPrice = new BigDecimal("12");

    public BigDecimal didiCallCar(String username,String startingPoint,String endPoint,Long mileage,Double unitPrice){
        BigDecimal price = new BigDecimal(unitPrice*mileage.doubleValue()).add(startingPrice);
        System.out.println(username+"在 滴滴出行下单,出发点:"+startingPoint+",终点:"+endPoint+",总里程数:"+mileage+",总计话费:"+price+"元");
        return price;
    }
}
