package com.sz.bewater.practice.interview.cg.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("婚礼司仪开始预热婚礼现场");
        Object result = methodProxy.invokeSuper(o, objects);    //cglib可以看作是利用目标类的子类作为目标类的代理对象 所以这里用invokeSuper 来真正调用父类的方法
        System.out.println("婚礼仪式结束,婚庆公司负责送客");
        return result;
        //        请了婚庆公司(这里的婚庆公司就是代理) 对婚礼进行了增强 包括司仪+司机接送
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Marry.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        Marry proxy = (Marry) enhancer.create();
        proxy.marry();


    }
}
