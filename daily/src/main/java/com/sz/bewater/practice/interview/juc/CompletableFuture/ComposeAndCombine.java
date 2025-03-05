package com.sz.bewater.practice.interview.juc.CompletableFuture;

import cn.hutool.core.date.StopWatch;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/4
 */
public class ComposeAndCombine {

    static class UserDetail{


        Integer getUserId() throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
            return 1;
        }

        String getUserName(Integer userId) throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
            if (userId == 1){
                return "user1";
            }
            return "null";
        }

    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UserDetail user = new UserDetail();
        CompletableFuture<String> composeFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return user.getUserId();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenCompose(res -> CompletableFuture.supplyAsync(() -> {
            try {
                return user.getUserName(res);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        System.out.println("主线程逻辑");
        String username = composeFuture.get(); //调用get方法阻塞至返回异步结果
        System.out.println(username);
        //将第一个CompletableFuture的结果作为第二个CompletableFuture的输入
        //thenCompose将CompletableFuture<Integer>变成CompletableFuture<String> 避免出现CompletableFuture<CompletableFuture>
        //这时候使用thenApply就不行了 就会导致嵌套的CompletableFuture<CompletableFuture<String>>
//        CompletableFuture<CompletableFuture<String>> completableFutureCompletableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                return user.getUserId();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).thenApply(res -> CompletableFuture.supplyAsync(() -> {
//            try {
//                return user.getUserName(res);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }));



        //thenCombine是将两个CompletableFuture的结果组合使用
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "bewater");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " so nice");
        String combine = future1.thenCombine(future2, (u1, u2) -> u1 + u2).get();
        System.out.println(combine);
    }


}
