package com.sz.bewater.practice.interview.framework.spring.lifecycle.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
public class MyBean implements BeanNameAware, BeanFactoryAware,ApplicationContextAware, InitializingBean,CommonInterface{

    private String name;

    public MyBean() {
        System.out.println("构造方法invoking");
    }

    public String getName() {
        return name;
    }

    @Value("bewater") //依赖注入 属性赋值为bewater
    public void setName(String name){
        this.name = name;
        System.out.println("setName invoking");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory invoking");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName invoking");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet invoking");
    }

    @PostConstruct
    public void init(){
        System.out.println("init invoking");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext invoking");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy invoking");
    }

    public void method(){
        System.out.println("我是目标类方法method");
    }
}
