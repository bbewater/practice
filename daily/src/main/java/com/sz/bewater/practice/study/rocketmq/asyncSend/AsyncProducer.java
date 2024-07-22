package com.sz.bewater.practice.study.rocketmq.asyncSend;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {

        // TODO: 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer();
        // TODO: 配置namesrv地址  配置一个也行(多个namesrv里面的数据是一致的)  配置多个 更好 以备一个时不可用
        producer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 配置重试次数为0 即不重试
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // TODO: 2024/7/20 设定topic下的队列数量
        producer.setDefaultTopicQueueNums(2);
        // TODO: 2024/7/20 设置生产者组
        producer.setProducerGroup("bewater_async_producer_group");
        // TODO: 2024/7/20 启动生产者
        producer.start();

        CountDownLatch latch = new CountDownLatch(100);
        // TODO: 2024/7/20 异步发送一百条消息
        for (int i = 0; i < 100; i++) {
            Message message = new Message("async_topic1","async_tag",("hi"+i).getBytes());
            message.setKeys("key"+i);
            producer.send(message, new SendCallback() {
                //发送成功的回调
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功=="+sendResult);
                    latch.countDown();
                }

                //发送失败的回调
                @Override
                public void onException(Throwable e) {
                    System.out.println("发送失败,原因==="+e);
                    latch.countDown();

                }
            });


        }
        latch.await();
        System.out.println("消息全部发送完成");
        // TODO: 2024/7/20 关闭生产者
        producer.shutdown();



    }
}
