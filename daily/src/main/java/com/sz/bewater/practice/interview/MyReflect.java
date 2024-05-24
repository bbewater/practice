package com.sz.bewater.practice.interview;

public class MyReflect {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //Class.forName
        Class<?> class1 = Class.forName("com.sz.bewater.practice.interview.MyOperator");
        //.class
        Class<MyOperator> class2 = MyOperator.class;
        MyOperator myOperator1 = class2.newInstance();
        //对象.getClass
        MyOperator myOperator = new MyOperator();
        Class<? extends MyOperator> class3 = myOperator.getClass();
        //classLoader 获取上下文类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> class4 = classLoader.loadClass("com.sz.bewater.practice.interview.MyOperator");
//        设置线程上下文加载器  可以打破双亲委派模型
//        Thread.currentThread().setContextClassLoader(classLoader);
    }
}
