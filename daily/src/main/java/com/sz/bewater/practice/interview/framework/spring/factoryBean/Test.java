package com.sz.bewater.practice.interview.framework.spring.factoryBean;

import com.sz.bewater.practice.interview.framework.spring.lifecycle.config.MySpringConfig;
import com.sz.bewater.practice.interview.framework.spring.lifecycle.spring.MyBeanPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

public class Test {

    public static void main(String[] args) throws Exception {
//        MyFactoryBean factoryBean = new MyFactoryBean();
//        CommonInterface object = factoryBean.getObject();
//        System.out.println(object);
//        FactoryBean 是一个特殊的 Bean，它本身是一个工厂，用于创建其他 Bean。通过实现 FactoryBean 接口，可以自定义创建复杂 Bean 的逻辑。
//        这里利用FactoryBean根据不同的需要创建不同的bean

        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
//        Object myFactoryBean = context.getBean("myFactoryBean");    //UserService@2096
        //通过容器获取FactoryBean其实是获取到其中getObject方法返回的bean类型
        //若要是想要获取FactoryBean则需要这样去获取
        context.getBean("myFactoryBean");  //MyFactoryBean@2094

        //ApplicationContext在容器启动的时候就完成了bean的实例化及初始化
        //对于BeanFactory的延迟初始化(实例化)
        /**
         * 1.注册注解处理器
         * AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory) 向容器注册处理 @Configuration、@Bean 等注解的必要后置处理器。
         * 2.执行 BeanDefinitionRegistryPostProcessor
         * 触发 ConfigurationClassPostProcessor 解析配置类，注册 @Bean 方法的 Bean 定义。
         * 3.执行 BeanFactoryPostProcessor
         * 处理其他 Bean 工厂级别的后置逻辑。
         * 4.预实例化单例 Bean
         * preInstantiateSingletons() 实例化所有单例 Bean（类似 ApplicationContext 的启动行为）。
         */
        //可以看出比较麻烦 而ApplicationContext已经内部封装好了
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 1. 注册必要的后置处理器（如 ConfigurationClassPostProcessor）
//        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
//        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
//        reader.register(MySpringConfig.class);
//        // 2. 手动触发 BeanFactoryPostProcessor 处理配置类
//        beanFactory.getBeansOfType(BeanDefinitionRegistryPostProcessor.class)
//                .values()
//                .forEach(processor -> processor.postProcessBeanDefinitionRegistry(beanFactory));
//
//        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class)
//                .values()
//                .forEach(processor -> processor.postProcessBeanFactory(beanFactory));
//
//        // 3. 初始化单例 Bean
//        beanFactory.preInstantiateSingletons();
//        beanFactory.getBean("myFactoryBean");



    }
}
