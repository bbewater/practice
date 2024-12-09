package com.sz.bewater.practice.interview.basic.simpleCopyWithDeepCopy.simpleCopy;

public class Test {


    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("bewater",20,new Address("beijing"));
        Person p2 = (Person)p1.clone();
        p2.setName("qiyana");
        p2.setAge(30);
        p2.getAddress().setCity("shanghai");

        System.out.println(p1.getName());   //bewater
        System.out.println(p1.getAge()); //20
        System.out.println(p1.getAddress());    //shanghai
//          默认是浅拷贝
//        浅拷贝会拷贝基本类型字段的副本 所以对于基本类型的修改不会影响原对象的属性值
//        浅拷贝对于引用类型字段是拷贝其引用  所以对于引用类型的修改是会影响原对象的属性值
//        string类型虽然是引用类型 但是它比较特殊 定义是final 每次操作都会产生新的对象 所以不会影响原来的字段值

        //基本类型和string相当于拷贝了份新的 不会影响原来的  引用类型由于还是浅拷贝 并不是新的 所以会影响原来的
        //深拷贝和浅拷贝的区别就在于引用类型的处理 深拷贝就是针对引用类型做了更深层次的拷贝 引用类型也拷贝了一份新的

    }
}
