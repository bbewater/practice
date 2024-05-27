package com.sz.bewater.practice.interview.framework;
public class MyBatisDemo {


    public static void main(String[] args) {
//        mybatis的缓存 1、2、3级缓存
//        一级缓存是会话级别的(默认开启)
//        二级缓存是mapper级别的(默认关闭 需手动开启)
//        三级缓存需要自己去拓展(实现cache接口)
//        二级缓存 是将命中的数据存放在内存中的(有个Map对象在PerpetualCache中) 在单体架构没什么问题 但是在分布式或者集群架构 当对数据进行更新时容易造成缓存不一致问题
//        而三级缓存是对二级缓存进行拓展 实现cache接口 可将数据存在在例如redis的中间件中 不会造成缓存不一致问题
//        先走二级缓存还是先走一级缓存??? 先走二级 因为二级缓存是mapper级别的 命中率较高 为了效率 是先走二级再走一级


//        SqlSession线程安全问题
//          为什么线程不安全??? 本身基于Jdbc中的Connection对象来实现的  Connection对象是线程不安全的 所以SqlSession也是线程不安全的
//        怎么解决? 1.作为局部变量放在方法内部 2.或者使用ThreadLocal
//        spring中是如何解决的?
//        Spring中用到了SqlSessionTemplate这个SqlSession的实现类 每次获取SqlSession对象都是通过SqlSessionUtils来获取新的SqlSession对象 多个线程相互独立
//        其实底层也是用到了ThreadLocal(TransactionSynchronizationManager)
//        虽然 SqlSessionTemplate 本身不直接使用 ThreadLocal，但它依赖的 Spring 事务管理机制在底层使用了 ThreadLocal 来管理资源绑定和事务同步


    }
}
