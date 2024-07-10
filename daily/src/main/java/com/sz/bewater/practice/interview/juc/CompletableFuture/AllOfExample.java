package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class AllOfExample {
    //allOf 用于等待所有 CompletableFuture 都完成。
    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Future 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Future 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2);
        System.out.println("main");
        allOfFuture.join();
        System.out.println("All futures completed");
    }
}
