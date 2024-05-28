package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.*;

public class MyCachedThreadPool {
//        public static ExecutorService newCachedThreadPool() {
//        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                                      60L, TimeUnit.SECONDS,
//                                      new SynchronousQueue<Runnable>());
//    }
//
//    CachedThreadPool 无核心线程数  最大线程数为Integer.MAX_VALUE 随着线程数不断增多会造成OOM
//    非核心线程数据空闲时间为60s
//    用到的阻塞队列是SynchronousBlockingQueue   不存储任务 一个put对应一个take  来任务的时候 假如此时有线程正在take 则直接复用该线程 假如没有则创建一个非核心线程

//    CachedThreadPool 适用于大量短期异步任务的场景。它可以快速响应大量突发任务请求，但需要注意资源管理，以避免线程数量无限增长。
//    CachedThreadPool 优先复用空闲线程，这样可以减少线程创建的开销，提高性能。当所有线程都在处理任务时，新的任务会触发新线程的创建。

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <20; i++) {
            Future submit = cachedThreadPool.submit(new Task());
            System.out.println(submit.get());
        }
        //关闭线程池
        cachedThreadPool.shutdown();

    }





    static class Task implements Callable {

        @Override
        public Object call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            return Thread.currentThread().getName()+"执行完毕";
        }
    }
}
