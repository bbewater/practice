package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.*;

public class MyCallableThread implements Callable {

    @Override
    public Object call() throws Exception {
        return "这是thread的返回值";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //需搭配FutureTask来使用
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallableThread());
        Thread callThread = new Thread(futureTask);
        callThread.start();
//        使用futureTask.get()获取返回值
        String returnMsg = futureTask.get(); //会阻塞
        System.out.println(returnMsg);

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(callThread);
        System.out.println(futureTask.get());
//        继承Thread 实现Runnable、Callable 线程池 其实都是基于Runnable来实现的


    }
}
