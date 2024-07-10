package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class RunAsyncExample {
//    runAsync 用于异步执行一个不返回结果的任务。
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task executed asynchronously");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("main");
        future.join(); // Wait for the task to complete
    }
}
