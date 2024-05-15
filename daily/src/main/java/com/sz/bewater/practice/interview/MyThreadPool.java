package com.sz.bewater.practice.interview;

public class MyThreadPool {


    public static void main(String[] args) {
        //两种提交任务的方式 execute 和 submit
        //1. execute 传的的runnable  submit 传的是 callable
        //2.execute执行其实是 runWorker方式直接调用task中的run方法 遇到异常向上抛出 当run方法结束  工作线程也就结束了
        //3.submit方式 是通过FutureTask 调用task的call方法  遇到异常通过setException方法 将异常存放到FutureTask中的outcome对象 不会向上抛出
        //  当遇到异常时 线程也不会结束(该线程可以继续处理下一个任务)

    }
}
