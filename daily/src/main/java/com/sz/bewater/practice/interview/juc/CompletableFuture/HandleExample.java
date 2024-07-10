package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class HandleExample {
//    handle 用于在 CompletableFuture 完成后对结果或异常进行处理，并返回一个新的结果。
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Exception occurred");
            }
            return "Hello";
        }).handle((result, exception) -> {
            if (exception != null) {
                return "Error: " + exception.getMessage();
            }
            return result;
        });

        System.out.println(future.join());
    }
}
