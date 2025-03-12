package com.sz.bewater.practice.interview.framework.spring.lifecycle.spring;

import com.sz.bewater.practice.interview.framework.spring.lifecycle.config.MySpringConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/12
 */
@Component
public class MySpringBean implements BeanNameAware,
        InitializingBean, DisposableBean,
        ApplicationContextAware, BeanFactoryAware {


    @PostConstruct
    public void init(){
        System.out.println("PostConstruct init");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware");
    }

    public MySpringBean(){
        System.out.println("MySpringBean constructor");
    }


    @Transactional
    public  void business(){
        System.out.println("business");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig.class);
        MySpringBean bean = applicationContext.getBean(MySpringBean.class);
        bean.business(); //MySpringBean$$EnhancerBySpringCGLIB 目标方法加了@Transactional注解 spring就会自动帮我们创建目标对象的代理对象 在初始化之后
    }


}
