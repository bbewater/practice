package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.stereotype.Component;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
public class T3CallCar {
    public static final double startingPrice = 10d;

    public Double t3CallCar(String username,String startingPoint,String endPoint,Long mileage,Double unitPrice){
        Double price = unitPrice*mileage.doubleValue()+startingPrice;
        System.out.println(username+"在 t3出行下单,出发点:"+startingPoint+",终点:"+endPoint+",总里程数:"+mileage+",总计话费:"+price+"元");
        return price;
    }
}
