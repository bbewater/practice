package com.sz.bewater.practice.interview.framework.spring.factoryBean;

import com.sz.bewater.practice.interview.framework.spring.lifecycle.config.MySpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) throws Exception {
//        MyFactoryBean factoryBean = new MyFactoryBean();
//        CommonInterface object = factoryBean.getObject();
//        System.out.println(object);
//        FactoryBean 是一个特殊的 Bean，它本身是一个工厂，用于创建其他 Bean。通过实现 FactoryBean 接口，可以自定义创建复杂 Bean 的逻辑。
//        这里利用FactoryBean根据不同的需要创建不同的bean

        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
        //Object myFactoryBean = context.getBean("myFactoryBean");    //UserService@2096
        //通过容器获取FactoryBean其实是获取到其中getObject方法返回的bean类型
        //若要是想要获取FactoryBean则需要这样去获取
        context.getBean("&myFactoryBean");  //MyFactoryBean@2094

    }
}
