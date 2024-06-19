package com.sz.bewater.practice.interview.basic.proxy.staticProxy;

public interface CommonService {

//  代理是一种设计模式  他的存在是为了对目标类做增强用的



//    静态代理： 1️⃣.编译期间就确定了代理类
//             2️⃣.目标类和代理类需实现相同借口
//              3️⃣.需要为每个目标类创建(维护)一个代理类
//    所以静态代理实现简单  但是冗余代码比较多 不好维护




    void method();

}
