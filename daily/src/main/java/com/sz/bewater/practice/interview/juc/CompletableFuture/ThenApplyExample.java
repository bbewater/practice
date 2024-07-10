package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    //thenApply 用于在 CompletableFuture 完成后对结果进行转换。
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(result -> result + ", World!");

        System.out.println("main");
        System.out.println(future.join());
    }
}
