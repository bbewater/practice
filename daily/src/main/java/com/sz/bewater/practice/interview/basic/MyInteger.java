package com.sz.bewater.practice.interview.basic;

public class MyInteger {


    public static void main(String[] args) {
        Integer a = 127,b = 127,c = 128,d = 128;
        System.out.println(a == b);
        System.out.println(c == d);
//        Integer是包装类型 == 比较的是引用地址 而有了IntegerCache缓存的存在 a和b指向了缓存中同一个引用地址 所以==返回true
//        但是这个缓存是有范围的(-128-127之间 包含)  c和d不在这个范围 所以返回false  这个范围可修改 -XX:AutoBoxCacheMax
//        这个缓存机制的存在 是为了 效率和节约内存 避免频繁创建Integer对象  减少了对象的创建和垃圾回收操作，提高了性能
//        是Integer包装类 自动装箱的体现  还有一些包装类 也有类似与这样的缓存存在:
//          Integer：
//
            //默认缓存范围：-128 到 127。
            //缓存机制：在 Integer 类的内部静态类 IntegerCache 中实现。
            //Byte：
            //
            //缓存范围：所有 Byte 值（-128 到 127）。
            //缓存机制：在 Byte 类的静态初始化块中实现，所有 Byte 对象都被缓存。
            //Short：
            //
            //默认缓存范围：-128 到 127。
            //缓存机制：在 Short 类的静态初始化块中实现。
            //Character：
            //
            //缓存范围：从 \u0000 到 \u007F（即 0 到 127）。
            //缓存机制：在 Character 类的静态初始化块中实现。
            //Long：
            //
            //默认缓存范围：-128 到 127。
            //缓存机制：在 Long 类的静态初始化块中实现。
            //Boolean：
            //
            //缓存范围：true 和 false 两个值。
            //缓存机制：在 Boolean 类中实现，只有两个常量 Boolean.TRUE 和 Boolean.FALSE。
            //其他包装类，如 Float 和 Double，没有类似的缓存机制。它们没有提供像 IntegerCache 这样的缓存，通常不会缓存任何特定的值。
            Long a1 = 127L,b1 = 127L,c1 = 128L,d1 = 128L;
            System.out.println(a1 == b1);   //true
            System.out.println(c1 == d1);   //false

    }
}
