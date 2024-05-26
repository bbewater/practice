package com.sz.bewater.practice.interview;

public class MyOperator {

//    &位与运算  当两个操作数的对应位都为 1 时，结果位才为 1，否则结果位为 0。 同为1则为1  否则为0
//    |位或运算  有1则1 无1则0
//    %取模   取余数
//    ^异或   不同则为1  相同为0

//    素数(质数)  除了1和它本身 无其他公约数 如2 3 5 7
//    合数  除了1和他本身 还有其他公约数   如4 6
//    1既不是素数也不是合数

    public static void main(String[] args) {
        int a = 10;  // 1010
        int b = 11;  // 1011
        System.out.println(a&b);    //1010  10
        System.out.println(a|b);    //1011  11
        System.out.println(a^b);    //0001  1
        System.out.println(a%b);    //10
        System.out.println(b%a);    //1

//        hashMap的length为2的次幂的好处 h&length-1  等价于h%length  而且位与&比取模更高效
//        为了计算key在Node[]上的位置 一般都是hash来对数组长度length取模 但是取模本身不高效  涉及将length设计成2的次幂 是为了将取模运算优化成位与运算
//        hash&length-1
        System.out.println(5%16);   //5
        System.out.println(5&15);   //1111  0101    0101  5

    }
}
