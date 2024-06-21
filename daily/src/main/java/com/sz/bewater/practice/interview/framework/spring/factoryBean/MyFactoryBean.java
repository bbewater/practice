package com.sz.bewater.practice.interview.framework.spring.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<CommonInterface> {
    private String serviceType;

    public MyFactoryBean(String serviceType) {
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
