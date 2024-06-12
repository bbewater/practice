package com.sz.bewater.practice.interview.juc;

public class SynchronizedClass {


    public static void main(String[] args) {
//        javac SynchronizedClass.java
//        javap -v SynchronizedClass.class
//        ObjectMonitor: owner(获取锁的线程)    cxq entryList(阻塞队列)  recursions(可重入锁 重入次数)
//        ObjectMonitor是 JVM 内部的一个数据结构，表示对象监视器。每个对象在升级为重量级锁时，会关联一个 ObjectMonitor 实例。
//        对象头中的markword 会记录指向jvm中相应的对象监视器objectMonitor的指针
//        cmpxchg 多核还需要lock指令 保证原子性 站在java角度 UnSafe类compareAndSwapInt方法  底层还是c++中的cmpxchg搭配lock(多核)

//        锁升级  无锁 -> 偏向锁 -> 轻量级锁(基于CAS自旋) -> 重量级锁(阻塞线程 扔到阻塞队列 cxq entryList 由用户态切换到内核态 由操作系统调度)
        synchronized (SynchronizedClass.class){

            System.out.println("hello");
        }

//          public static void main(java.lang.String[]);
//    descriptor: ([Ljava/lang/String;)V
//    flags: ACC_PUBLIC, ACC_STATIC
//    Code:
//      stack=2, locals=1, args_size=1
//         0: ldc           #2                  // class SynchronizedClass
//         2: dup
//         3: monitorenter
//         4: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
//         7: ldc           #4                  // String hello
//         9: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
//        12: monitorexit
//        13: goto          21
//        16: astore_1
//        17: aload_1
//        18: monitorexit
//        19: athrow
//        20: return
//      Exception table:
//         from    to  target type
//             4    13    16   any
//            16    19    16   any
//      LineNumberTable:
//        line 12: 0
//        line 13: 4
//        line 14: 12
//        line 15: 20
//      LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//               0      21     0  args   [Ljava/lang/String;

//        当你使用 javap -v 命令来反编译字节码时，你会看到关于 ObjectMonitor 的信息，
//        这是因为 synchronized 块在字节码级别会被编译成特定的指令序列，其中包括进入和退出对象监视器的操作。
//        具体地说，synchronized 块会被编译成使用 monitorenter 和 monitorexit 指令：
//        即使没有多线程介入竞争锁，因此在运行时并不会创建实际的 ObjectMonitor 实例。
//        然而，字节码中仍然会包含监视器进入和退出的指令，表示潜在的锁机制


    }
}
