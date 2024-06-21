package com.sz.bewater.practice.interview.framework.spring.factoryBean;

public class Test {

    public static void main(String[] args) throws Exception {
//        MyFactoryBean factoryBean = new MyFactoryBean("admin");
        MyFactoryBean factoryBean = new MyFactoryBean("user");
        CommonInterface object = factoryBean.getObject();
        System.out.println(object);
//        FactoryBean 是一个特殊的 Bean，它本身是一个工厂，用于创建其他 Bean。通过实现 FactoryBean 接口，可以自定义创建复杂 Bean 的逻辑。
//        这里利用FactoryBean根据不同的需要创建不同的bean


    }
}
