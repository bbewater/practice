package com.sz.bewater.practice.interview.juc;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayQueue {
//    无界的延迟阻塞队列  延迟时间最短的最新消费  队列里的元素 需实现Delayed接口 重写getDelay和compareTo方法
//    延迟时间最短的元素最先消费
    private static final BlockingQueue<DelayExample> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);  //模拟生产者准备
                delayQueue.put(new DelayExample(2000L,"延迟消息1"));
                delayQueue.put(new DelayExample(1000L,"延迟消息2"));
                delayQueue.put(new DelayExample(500L,"延迟消息3"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        },"producer").start();

        new Thread(() -> {
            System.out.println("消费者等待中......");
            while(true){
                try {
                    DelayExample take = delayQueue.take();
                    System.out.println("消费者消费:"+JSON.toJSONString(take));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        },"consumer").start();


        DelayExample delayExample = new DelayExample(5000L, "测试消息");
        System.out.println("剩余延迟时间为:"+delayExample.getDelay(TimeUnit.SECONDS));


    }





    @Data
    static class DelayExample implements Delayed{
        private Long delayTime;
        private Long expireTime;
        private String desc;


        public DelayExample(Long delayTime, String desc) {
            this.delayTime = delayTime;
            this.desc = desc;
            this.expireTime = delayTime+System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();    //计算剩余的延迟时间
            return unit.convert(diff,TimeUnit.MILLISECONDS);    //时间格式按照调用者传入的时间进行转换
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.expireTime > ((DelayExample)o).expireTime){
                return 1;
            }
            if (this.expireTime < ((DelayExample)o).expireTime){
                return -1;
            }
            return 0;
        }
    }

}
