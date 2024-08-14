package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.deepCopy;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("bewater", new Address("beijing"));
        Person p2 = (Person)p1.clone();
        p2.setName("qiyana");
        p2.getAddress().setCity("shanghai");
        System.out.println(p1.getName()); //bewater
        System.out.println(p1.getAddress()); //beijing

        //深拷贝需要在clone方法里面手动再对引用类型进行clone
//        深拷贝对于基本类型或者是引用类型都会拷贝一份 拷贝出来的对象相当于是一个新的对象了 所以新对象不管怎么变都不会影响原对象

    }
}
