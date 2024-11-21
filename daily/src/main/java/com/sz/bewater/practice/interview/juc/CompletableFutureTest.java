package com.sz.bewater.practice.interview.juc;

import org.checkerframework.checker.optional.qual.Present;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2024/11/20
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runAsync 开启一个无返回值异步任务
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("runAsync"));
        //supplyAsync 开启一个有返回值的异步任务
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "1");

        //thenAccept 异步任务成功后 对结果进行消费 无返回值 侧重于消费
        CompletableFuture<Void> thenAccept = supplyAsync.thenAccept(res -> System.out.println(res+" thenAccept 消费"));

        
        //thenApply 异步任务成功后 对结果进行处理 有返回值 可返回与原返回值不同类型的返回值 侧重于转换处理
        CompletableFuture<Integer> integerCompletableFuture = supplyAsync.thenApply(Integer::parseInt);

        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return "异步任务1执行完成";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return "异步任务2执行完成";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //anyOf 任意一个异步任务执行完成即可 返回执行完成的异步任务的返回值
        Object join = CompletableFuture.anyOf(supplyAsync1, supplyAsync2).join();
        System.out.println(join);
//        anyAsyncTask.thenAccept(res -> System.out.println("有任务执行完成 -- res: " + res));


        CompletableFuture<Void> runAsync3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("异步任务3执行完成");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> runAsync4 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("异步任务4执行完成");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //allOf 待全部的异步任务执行完成 无返回值
        CompletableFuture.allOf(runAsync3, runAsync4).join();

        //thenRun 表示异步任务执行完之后的动作 不依赖于异步任务的执行结果
        CompletableFuture.supplyAsync(() -> "nihao").thenRun(() -> System.out.println("异步任务执行完了"));

        //whenComplete 可以对异步任务和出现的异常做处理 返回一个新的CompletableFuture 注意返回值必须和原来的CompletableFuture相同
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "异步任务执行结果").whenComplete((res, ex) -> {
            if (res != null) {
                System.out.println(res);
            }
            if (ex != null) {
                ex.printStackTrace();
            }
        });

        //handle 与whenComplete作用相似 但是handle可以返回一个新的返回值与原来不同的CompletableFuture
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> "11").handle((res, ex) -> {
            Integer a = 0;
            if (res != null) {
                a = a + Integer.valueOf(res);
            }
            if (ex != null) {
                ex.printStackTrace();
            }
            return a;
        });
        Integer i = handle.get();
        System.out.println(i);
    }
}
