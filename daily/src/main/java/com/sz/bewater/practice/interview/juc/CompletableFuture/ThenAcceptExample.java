package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
//    thenAccept 用于在 CompletableFuture 完成后对结果进行消费。
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenAccept(result -> System.out.println("Result: " + result));
    }
}
