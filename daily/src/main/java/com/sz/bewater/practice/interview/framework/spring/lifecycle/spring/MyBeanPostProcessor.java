package com.sz.bewater.practice.interview.framework.spring.lifecycle.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myBean")){
            System.out.println("postProcessBeforeInitialization invoking");
        }
        if (beanName.equals("mySpringBean")){
            System.out.println("postProcessBeforeInitialization invoking");
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myBean")){
            System.out.println("postProcessAfterInitialization invoking");
//            cglib做的动态代理
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyBean.class);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("增强Mybean");
                    //methodProxy.invokeSuper 调用父类方法时需要确保在代理对象的上下文中执行 所以这里是o而不是bean。直接使用目标对象 bean 会跳过代理逻辑，破坏代理机制的正确性。
                    //代理对象 o 包含了CGLIB生成的代理逻辑，包括拦截和增强的逻辑，使用 o 能确保这些逻辑被正确执行。
                    Object result = methodProxy.invokeSuper(o, objects);    //调用目标类也就是父类的方法
                    System.out.println("增强Mybean");
                    return result;
                }
            });
            MyBean proxy = (MyBean)enhancer.create();
            proxy.method();
            return proxy;



//            jdk做的动态代理
//            CommonInterface proxy = (CommonInterface) Proxy.newProxyInstance(MyBean.class.getClassLoader(), MyBean.class.getInterfaces(), new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    System.out.println("增强MyBean的method");
//                    Object result = method.invoke(bean, args);  //直接调用目标对象的方法 所以这里是bean而不是proxy
//                    System.out.println("增强MyBean的method");
//                    return result;
//                }
//            });
//            proxy.method();
//            return proxy;
        }
        if (beanName.equals("mySpringBean")){
            System.out.println("postProcessAfterInitialization invoking");
        }
        return bean;
    }
}
