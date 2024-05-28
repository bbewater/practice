package com.sz.bewater.practice.interview.juc;

import java.util.concurrent.*;

public class MyFixedThreadPool {
//       public static ExecutorService newFixedThreadPool(int nThreads) {
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                                      0L, TimeUnit.MILLISECONDS,
//                                      new LinkedBlockingQueue<Runnable>());
//    }
//    FixedThreadPool 核心线程和最大工作线程相等 即全是核心线程
//    0L 非核心线程空闲时的保活时间  因无非核心线程 故为0
//    用的阻塞队列是LinkedBlockingQueue  默认是无界的  也即不会拒绝任务  假如无空闲的核心线程 都会扔在队列里 OOM


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Future future = fixedThreadPool.submit(new Task());
            System.out.println(future.get());
        }
        //关闭线程池
        fixedThreadPool.shutdown();


    }


    static class Task implements Callable{


        @Override
        public Object call() throws Exception {
            return Thread.currentThread().getName()+"执行完毕";
        }
    }
}
