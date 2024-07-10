package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class AnyOfExample {
//    anyOf 用于等待多个 CompletableFuture 中的任意一个完成。
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future 1";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Future 2";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
        System.out.println("main");
        System.out.println(anyOfFuture.join());
    }
}
