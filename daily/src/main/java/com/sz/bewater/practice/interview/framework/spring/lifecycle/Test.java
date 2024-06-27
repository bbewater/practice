package com.sz.bewater.practice.interview.framework.spring.lifecycle;

import com.sz.bewater.practice.interview.framework.spring.lifecycle.config.MySpringConfig;
import com.sz.bewater.practice.interview.framework.spring.lifecycle.spring.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
        Object myBean = context.getBean("myBean");      //注意这里 假如说是使用cglib动态代理并返回代理对象这里可以强转为目标类  因为代理类是目标类的字类  而假如使用了jdk动态代理 这里不能强转为目标对象 因为jdk动态代理代理类和目标类只是实现了相同的接口
//        System.out.println(myBean.getName());

    }
}
