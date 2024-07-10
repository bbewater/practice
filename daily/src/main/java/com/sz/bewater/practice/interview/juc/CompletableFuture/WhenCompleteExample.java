package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteExample {
//    whenComplete 用于在 CompletableFuture 完成后执行一个动作，能够访问到结果和异常。
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        System.out.println("Result: " + result);
                    } else {
                        exception.printStackTrace();
                    }
                });
    }
}
