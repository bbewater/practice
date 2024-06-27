package com.sz.bewater.practice.interview.framework.spring.lifecycle;

import com.sz.bewater.practice.interview.framework.spring.lifecycle.config.MySpringConfig;
import com.sz.bewater.practice.interview.framework.spring.lifecycle.spring.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
        Object myBean = context.getBean("MymyBean");      //注意这里 假如说是使用cglib动态代理并返回代理对象这里可以强转为目标类  因为代理类是目标类的字类  而假如使用了jdk动态代理 这里不能强转为目标对象 因为jdk动态代理代理类和目标类只是实现了相同的接口
//        System.out.println(myBean.getName());

// 具体来说，如果类名的前两个或以上字母都是大写，那么默认的 Bean 名称与类名一样；如果类名的第一个字母大写而第二个字母小写，或者类名的第一个字母是小写，那么默认情况下会将首字母转换为小写作为 Bean 的名称。
//例如，对于类名HelloService，其默认的 Bean 名称为helloService；而对于类名ABService，其默认的 Bean 名称就是ABService。
//但如果在定义 Bean 时显式指定了名称，例如使用@Service("customBeanName")这样的注解，那么 Bean 的名称就是指定的customBeanName，而不受默认规则的限制。

    }
}
