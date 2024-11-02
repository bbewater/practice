package com.sz.bewater.practice.interview.juc.CompletableFuture;

import com.sz.bewater.practice.interview.juc.MyCallableThread;

import java.util.concurrent.*;

public class MyZDCallThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "bewater is great";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        FutureTask<String> futureTask = new FutureTask<String>(new MyZDCallThread());
//        new Thread(futureTask).start();
//        String s = futureTask.get();    //get会阻塞至结果返回 可以传入时间参数 指定阻塞时间 时间到了还是获取不到则会报错TimeoutException
//        System.out.println("s is : "+s);

//        或者利用futureTask.isDone来判断任务是否执行完
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<?> future = threadPool.submit(new MyZDCallThread());
        while(!future.isDone()){
            System.out.println("doing another things");
        }
        System.out.println(future.get());

    }
}
