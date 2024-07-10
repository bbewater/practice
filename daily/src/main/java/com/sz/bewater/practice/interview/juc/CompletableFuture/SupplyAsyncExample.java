package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class SupplyAsyncExample {
//    supplyAsync 用于异步执行一个返回结果的任务。
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, World!";
        });
        System.out.println("main");
        String result = future.join();
        System.out.println(result);
    }
}
