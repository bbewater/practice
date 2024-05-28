package com.sz.bewater.practice.interview.juc;

public class MyThreadPool {


    public static void main(String[] args) {
        //两种提交任务的方式 execute 和 submit
        //1. execute 传的的runnable  submit 传的是 callable
        //2.execute执行其实是 runWorker方式直接调用task中的run方法 遇到异常向上抛出 当run方法结束  工作线程也就结束了
        //3.submit方式 是通过FutureTask 调用task的call方法  遇到异常通过setException方法 将异常存放到FutureTask中的outcome对象 不会向上抛出
        //  当遇到异常时 线程也不会结束(该线程可以继续处理下一个任务)

//        ThreadPoolExecutor  ctl(4个字节 32bit位)  存储两个变量  高3位:线程池状态 低29位线程池中线程数量(2^29-1)
//        线程池状态: RUNNING SHUTDOWN STOP TIDYING TERMINATED
//        RUNNING
//        SHUTDOWN: 不接受新任务 等待队列中任务执行完 (调用shutdown()方法 变成这个状态)
//        STOP: 不接受新任务 尝试中断队列中中的任务 (调用shutdownNow()方法 变成这个状态)
//        TIDYING: 过度状态 当所有任务都结束 到达这个状态  用户可拓展(terminated()方法 空方法 业务拓展)
//        TERMINATED: 最终状态  整个线程池关闭

        System.out.println(Runtime.getRuntime().availableProcessors()); //cpu核数


    }
}
