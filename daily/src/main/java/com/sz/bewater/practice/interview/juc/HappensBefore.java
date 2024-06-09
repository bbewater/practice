package com.sz.bewater.practice.interview.juc;

public class HappensBefore {
    private static String s;

    public static void main(String[] args) {
        //在启动新线程之前，主线程执行了操作A。
        //线程启动后执行操作B。
        //因此，操作A happens-before 操作B，确保操作A的结果对线程t中的操作B是可见的。

        new Thread(() -> {
            System.out.println(s);  //操作B
        }).start();

        s = "zd";   //操作A


//        happens-before原则的核心是确保在一个线程中或多个线程之间某个操作的结果对随后操作是可见的，并且按一定顺序执行。
//        这个原则在多线程编程中非常重要，因为它帮助我们理解和控制不同线程之间的交互，确保程序的正确性和一致性。

    }
}
