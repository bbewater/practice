package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
//  whenComplete和  handle 都会返回一个新的CompletableFuture  但是whenComplete返回的新的CompletableFuture和传进来的future返回值必须相同
//    而handle可以返回与传进来的future返回值不同的CompletableFuture

    //    thenApply、thenAccept 都可以原future进行消费 但是thenApply可以返回一个新的带返回值的CompletableFuture而thenAccept不可以
//    thenRun 不依赖于原future的返回值 强调的是原future完成后要做的事
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Result from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return "Result from future2";
        });

        // 使用 thenApply 处理结果
        CompletableFuture<String> future3 = future1.thenApply(result -> result + " processed");

        // 使用 thenAccept 处理结果
        future3.thenAccept(result -> System.out.println("thenAccept: " + result));

        // 使用 thenRun 处理完成事件
        future3.thenRun(() -> System.out.println("thenRun: future3 completed"));

        // 使用 whenComplete 处理完成事件和异常
        future2.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("whenComplete: " + result);
            } else {
                exception.printStackTrace();
            }
        });

        // 使用 handle 处理结果和异常
        CompletableFuture<String> future4 = future2.handle((result, exception) -> {
            if (exception != null) {
                return "Error: " + exception.getMessage();
            }
            return result.toUpperCase();
        });

        System.out.println("handle: " + future4.get());

        // 使用 anyOf 等待任意一个完成
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
        System.out.println("anyOf: " + anyOfFuture.get());

        // 使用 allOf 等待所有完成
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2);
        allOfFuture.join(); // 等待所有任务完成
        System.out.println("allOf: All tasks completed");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
