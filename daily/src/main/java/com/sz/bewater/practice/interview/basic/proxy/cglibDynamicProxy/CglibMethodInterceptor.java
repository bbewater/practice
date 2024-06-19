package com.sz.bewater.practice.interview.basic.proxy.cglibDynamicProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {

    //    动态代理:   1️⃣.代理类在运行期间通过反射动态生成(相较于静态代理需在编译期间确定代理类)
//              2️⃣.cglib动态代理依赖于Enhancer和MethodInterceptor
//              3️⃣.不需要为每个目标类创建一个单独的代理类了  减少了代码的冗余


//    MethodInterceptor是CGLIB中的一个接口，用于方法拦截。CGLIB通过生成目标类的子类并覆盖目标类的方法来实现动态代理。
//    因此，代理对象实际上是目标类的子类，方法调用会被拦截并由MethodInterceptor处理。

//    import org.springframework.cglib.proxy.Enhancer;  spring集成了cglib

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这是cglib动态代理打印前");
        Object invoke = methodProxy.invokeSuper(o,objects);//cglib可以看作是利用目标类的子类作为目标类的代理对象 所以这里用invokeSuper 来真正调用父类的方法
        System.out.println("这是cglib动态代理打印后");
        return invoke;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyCglibTarget.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        MyCglibTarget cglibProxy = (MyCglibTarget) enhancer.create();
        cglibProxy.method();


    }
}
