package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.*;

public class MySingleThreadPool {
//        public static ExecutorService newSingleThreadExecutor() {
//        return new FinalizableDelegatedExecutorService
//            (new ThreadPoolExecutor(1, 1,
//                                    0L, TimeUnit.MILLISECONDS,
//                                    new LinkedBlockingQueue<Runnable>()));
//    }
//
//SingleThreadExecutor  核心线程和最大核心线程都为1  可以理解为是FixedThreadPool的单机版(单线程版)
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            Future submit = singleThreadExecutor.submit(new Task());
            System.out.println(submit.get());
        }
        singleThreadExecutor.shutdown();

    }




    static class Task implements Callable {
        @Override
        public Object call() throws Exception {
            return Thread.currentThread().getName()+"执行完毕";
        }
    }
}
