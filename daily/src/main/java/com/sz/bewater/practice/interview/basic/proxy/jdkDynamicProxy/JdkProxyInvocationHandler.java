package com.sz.bewater.practice.interview.basic.proxy.jdkDynamicProxy;

import com.sz.bewater.practice.interview.basic.proxy.staticProxy.CommonService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyInvocationHandler implements InvocationHandler {
//    动态代理:   1️⃣.代理类在运行期间通过反射动态生成(相较于静态代理需在编译期间确定代理类)
//              2️⃣.JDK动态代理需依赖于反射和InvocationHandler接口
//              3️⃣.不需要为每个目标类创建一个单独的代理类了  减少了代码的冗余

//    这里描述的是jdk的动态代理  需要目标类实现一个或者多个接口

    private Object target;

    public JdkProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这是动态代理打印前");
        Object result = method.invoke(target, args);
        System.out.println("这是动态代理打印后");
        return result;
    }


    public static void main(String[] args) {
        CommonService target = new MyDynamicTarget();
        CommonService target2 = new MyDynamicTarget2();
        CommonService dynamicProxy = (CommonService)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JdkProxyInvocationHandler(target2));    //不需要一个目标类对应一个代理类了  可以多个目标类对应一个代理类
        dynamicProxy.method();


    }
}
