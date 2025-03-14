package com.sz.bewater.practice.interview.basic.adapter;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/14
 */
@Component
public class CallCarFactoryBean implements FactoryBean<CallCarService> {
    private final Map<String, CallCarService> callCarServiceMap;
    @Value("${call.type}")
    private String callType;


    public CallCarFactoryBean(List<CallCarService> callCarServices) {
        callCarServiceMap = callCarServices.stream()
                .collect(Collectors.toMap(item -> item.getClass().getAnnotation(CallType.class).value()
                , Function.identity()));
    }


    //注意适配器模式只解决接口不兼容问题 至于对象创建调用者是否感知 这个适配器模式不关心
    //可以搭配工厂模式一起使用 让客户端无感知的调用 见AdapterTest.java  我这里使用到了 FactoryBean 也是工厂模式
    /**
     * CallCarService callCarService = callCarFactoryBean.getObject();
     * callCarService.callCar("bewater","优公馆","苏州北站",12L,0.5d);
     */
    @Override
    public CallCarService getObject() throws Exception {

        String key = callCarServiceMap.keySet().stream().filter(item -> item.equalsIgnoreCase(callType))
                .findFirst().orElseThrow(() -> new RuntimeException("找不到匹配的 bean"));
        return callCarServiceMap.get(key);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
