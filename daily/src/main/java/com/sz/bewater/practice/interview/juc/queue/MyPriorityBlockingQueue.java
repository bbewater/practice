package com.sz.bewater.practice.interview.juc.queue;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MyPriorityBlockingQueue {
//    无界的按优先级的(可以理解为按我们自定义的顺序消费的)阻塞队列  阻塞队列中存放的元素需实现Comparable接口来自定义消费顺序
//    因为无界 所以不会阻塞生产者put  只会阻塞消费者take(当队列为空 阻塞至队列里有值为止)
//    如果指定了初始容量，但 PriorityBlockingQueue 可以根据需要动态增长，不会限制队列的最大容量。
//    这使得 PriorityBlockingQueue 既可以高效地处理初始数量较大的任务，又可以动态扩展以处理更多的任务。
//    数值越小 优先级越高
    private static final BlockingQueue<PriorityExample> priorityBlockingQueue = new PriorityBlockingQueue<>();


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                priorityBlockingQueue.put(new PriorityExample(2,"medium priority"));
                priorityBlockingQueue.put(new PriorityExample(1,"high priority"));
                priorityBlockingQueue.put(new PriorityExample(3,"low priority"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        },"producer").start();


        new Thread(() ->{
            while(true){
                try {
                    PriorityExample take = priorityBlockingQueue.take();
                    System.out.println("consumer consume item:"+JSON.toJSONString(take));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        },"consumer").start();



    }


    @Data
    static class PriorityExample implements Comparable<PriorityExample>{
        private Integer priority;
        private String desc;

        public PriorityExample(Integer priority, String desc) {
            this.priority = priority;
            this.desc = desc;
        }

        @Override
        public int compareTo(PriorityExample o) {
            return Integer.compare(this.priority,o.priority);
        }
    }


}
