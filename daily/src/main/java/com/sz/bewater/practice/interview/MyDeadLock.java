package com.sz.bewater.practice.interview;

public class MyDeadLock {
    public static void main(String[] args) {
        String resource1 = "resource1";
        String resource2 = "resource2";

//        两个线程 各自持有锁 不释放  还尝试获取对方的锁


        new Thread(() ->{
            synchronized (resource1){
                System.out.println("Thread1: get resource1");
                try {
                    Thread.sleep(100);  //线程休眠100ms 保证另一个线程可以拿到resource2
                    //休眠结束后 尝试对resource2加锁
                    synchronized (resource2){
                        System.out.println("Thread1: get resource2");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        ).start();



        new Thread(() ->{
            synchronized (resource2){
                System.out.println("Thread2: get resource2");
                try {
                    Thread.sleep(100);  //线程休眠100ms 保证另一个线程可以拿到resource1
                    //休眠结束后 尝试对resource1加锁
                    synchronized (resource1){
                        System.out.println("Thread2: get resource1");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        ).start();


//        jstack pid   Found 1 deadlock.


    }

}
