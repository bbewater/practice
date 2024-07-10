package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
//    thenRun 用于在 CompletableFuture 完成后执行一个动作，但不使用结果。
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenRun(() -> System.out.println("Task completed"));
    }
}
