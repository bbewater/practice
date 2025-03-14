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
@CallType("didi")
public class DiDiCalCarAdapter implements CallCarService{
    private DiDiCallCar diDiCallCar;

    public DiDiCalCarAdapter(DiDiCallCar diDiCallCar) {
        this.diDiCallCar = diDiCallCar;
    }

    @Override
    public Double callCar(String username, String startingPoint, String endPoint, Long mileage, Double unitPrice) {
        BigDecimal price = diDiCallCar.didiCallCar(username, startingPoint, endPoint, mileage, unitPrice);
        return price.doubleValue();
    }
}
