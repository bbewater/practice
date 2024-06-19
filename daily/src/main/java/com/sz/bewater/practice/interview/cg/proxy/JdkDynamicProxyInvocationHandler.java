package com.sz.bewater.practice.interview.cg.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public JdkDynamicProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("婚礼司仪开始预热婚礼现场");
        Object result = method.invoke(target, args);
        System.out.println("婚礼仪式结束,婚庆公司负责送客");
        return result;
//        请了婚庆公司(这里的婚庆公司就是代理) 对婚礼进行了增强 包括司仪+司机接送
    }

    public static void main(String[] args) {
        MarryInterface marry = new Marry();
        MarryInterface proxy = (MarryInterface)Proxy.newProxyInstance(marry.getClass().getClassLoader(),
                marry.getClass().getInterfaces(),
                new JdkDynamicProxyInvocationHandler(marry));
        proxy.marry();



    }
}
