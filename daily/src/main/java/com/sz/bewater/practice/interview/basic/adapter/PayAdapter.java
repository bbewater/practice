package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Project: practice
 * Description: 将第三方支付接口适配到系统支付接口
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
public class PayAdapter implements Payment{
    /**
     * 1. 定义目标接口
     * 确定系统需要统一的接口规范（如 Payment）。
     * 2. 实现适配器
     *   ○ 适配器类实现目标接口（implements Payment）。
     *   ○ 通过组合方式持有被适配者对象（ThirdPayment）。
     *   ○ 在目标接口方法中调用被适配者的方法，完成参数转换、逻辑适配。
     * 3. 客户端调用
     * 通过适配器对象调用目标接口方法，无需关心底层被适配者的实现细节。
     */

    //适配器模式本质：通过包装不兼容的接口，实现接口转换和复用 解决的是接口不兼容的问题

    private ThirdPayment thirdPayment;

    public PayAdapter(ThirdPayment thirdPayment) {
        this.thirdPayment = thirdPayment;
    }

    @Override
    public void pay(BigDecimal amount) {
        //重写共同的支付方法 再将不兼容的第三方支付的方法添加到里面 使得兼容
        thirdPayment.thirdPay(amount.doubleValue(), amount.doubleValue()*0.1);
    }
}
