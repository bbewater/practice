package com.sz.bewater.practice.interview.basic.proxy.jdkDynamicProxy;

import com.sz.bewater.practice.interview.basic.proxy.staticProxy.CommonService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyTarget implements CommonService {
    @Override
    public void method() {
        System.out.println("target B is calling");
    }


    public static void main(String[] args) {
//        CommonService proxyInstance = (CommonService)Proxy.newProxyInstance(MyTargetB.class.getClassLoader(), MyTargetB.class.getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("方法增强前");
//                        Object result = method.invoke(new MyTargetB(), args);
//                        System.out.println("方法增强后");
//                        return result;
//                    }
//                });
//        proxyInstance.method();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyTarget.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("方法增强前");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("方法增强后");
                return result;
            }
        });
        MyTarget proxyInstance = (MyTarget) enhancer.create();
        proxyInstance.method();

    }
}
