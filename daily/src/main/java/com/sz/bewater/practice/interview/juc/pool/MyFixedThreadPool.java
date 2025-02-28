package com.sz.bewater.practice.interview.juc.pool;

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
               System.out.println(future.get()); //get是阻塞的 会影响到后面的awaitTermination判断
        }
        //关闭线程池
        fixedThreadPool.shutdown();

//        if (!fixedThreadPool.awaitTermination(10,TimeUnit.SECONDS)){
//            //shutdown会拒绝新任务 但是不会终止正在运行的任务  这里最多等待30s 看任务是否全部处理完 并且线程池进入TERMINATED
//            //假如仍然没有 则强制关闭线程池 调用shutdownNow()方法
//            fixedThreadPool.shutdownNow();
//        }
//        System.out.println("all task is complete");


    }


    static class Task implements Callable{


        @Override
        public Object call() throws Exception {
//            TimeUnit.SECONDS.sleep(5);
            return Thread.currentThread().getName()+"执行完毕";
        }
    }
}
