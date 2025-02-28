package com.sz.bewater.practice.interview.juc.pool;

import java.util.concurrent.*;

public class MyScheduledThreadPool {
//        public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
//        return new ScheduledThreadPoolExecutor(corePoolSize);
//    }

//        public ScheduledThreadPoolExecutor(int corePoolSize) {
//        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
//              new DelayedWorkQueue());
//    }

//    ScheduledThreadPoolExecutor 用来执行延迟任务 指定核心线程数  最大线程数为Integer.MAX_VALUE
//    非核心线程数空闲时间为0 也即完成任务后就被销毁
//    DelayedWorkQueue  延迟阻塞队列 和DelayQueue相似
//    除了正常像submit execute提交任务  还提供了延迟提交任务的api方法
//    scheduleWithFixedDelay强调的是执行完任务后在提交  而scheduleAtFixedRate不管是否执行完 到时间了继续提交

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        Future future1 = scheduledExecutorService.submit(new Task());
//        System.out.println(future1.get());
//        //在2s后执行
//        ScheduledFuture future2 = scheduledExecutorService.schedule(new Task(), 2, TimeUnit.SECONDS);
//        System.out.println(future2.get());
        // 提交一个每 2 秒执行一次的任务，第一次延迟 1 秒执行
//        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("执行成功"), 1, 2, TimeUnit.SECONDS);
        // 提交一个每次执行完任务后延迟 2 秒再执行的任务，第一次延迟 1 秒执行  强调执行完任务后再提交
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("嗨,你好"),1,2,TimeUnit.SECONDS);

        //30s后关闭线程池
        scheduledExecutorService.schedule(scheduledExecutorService::shutdown,30,TimeUnit.SECONDS);

    }



    static class Task implements Callable {
        @Override
        public Object call() throws Exception {
            return Thread.currentThread().getName()+"执行完毕";
        }
    }
}
