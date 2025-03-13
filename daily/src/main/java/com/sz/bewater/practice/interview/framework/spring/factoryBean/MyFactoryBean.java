package com.sz.bewater.practice.interview.framework.spring.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<CommonInterface> {
    private String serviceType;

    //这里需要注意 当MyFactoryBean作为bean被spring管理时 那么通过构造方法实例化时 发现需要注入String类型的bean(beanName为serviceType)
    //而spring容器找不到 就会报错(No qualifying bean of type 'java.lang.String' available)
    // 则这时候需要通过@Value注解从配置中注入进来
    public MyFactoryBean(@Value("${serviceType}") String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public CommonInterface getObject() throws Exception {
        if (serviceType.equals("admin")){
            return new AdminService();
        }
        return new UserService();
    }

    @Override
    public Class<?> getObjectType() {
        return CommonInterface.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
