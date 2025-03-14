package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
@CallType("t3")
public class T3CallCarAdapter implements CallCarService{
    private T3CallCar t3CallCar;

    public T3CallCarAdapter(T3CallCar t3CallCar) {
        this.t3CallCar = t3CallCar;
    }

    @Override
    public Double callCar(String username, String startingPoint, String endPoint, Long mileage, Double unitPrice) {
        return t3CallCar.t3CallCar(username, startingPoint, endPoint, mileage, unitPrice);
    }
}
