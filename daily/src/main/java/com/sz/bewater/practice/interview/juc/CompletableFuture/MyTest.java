package com.sz.bewater.practice.interview.juc.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyTest {
//    runAsync 用于异步执行一个不返回结果的任务。
//    supplyAsync 用于异步执行一个返回结果的任务。


//  whenComplete和  handle 都会返回一个新的CompletableFuture
//  但是whenComplete返回的新的CompletableFuture和传进来的future返回值必须相同
//    而handle可以返回与传进来的future返回值不同的CompletableFuture

//    thenApply、thenAccept 都可以原future进行消费
//    但是thenApply可以返回一个新的带返回值的CompletableFuture而thenAccept不可以
//    thenRun 不依赖于原future的返回值 强调的是原future完成后要做的事

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "nihao");

//        CompletableFuture<Integer> complete = future1.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("成功");
//            } else {
//                System.out.println("异步出错");
//            }
//        });
//
//
//        CompletableFuture<String> handle = future2.handle((result, ex) -> {
//            return result.toUpperCase();
//        });
//
//        complete.join();
//        handle.join();
//        System.out.println(handle.get());
//        System.out.println(complete.get());

    }
}
