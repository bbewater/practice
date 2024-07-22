package com.sz.bewater.practice.study.rocketmq.syncSend;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class SyncProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // TODO: 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer();
        // TODO: 配置namesrv地址  配置一个也行(多个namesrv里面的数据是一致的)  配置多个 更好 以备一个时不可用
        producer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 配置重试次数 同步用
        producer.setRetryTimesWhenSendFailed(3);
        // TODO: 2024/7/20 配置发送超时时间
        producer.setSendMsgTimeout(5000);   //这个超时时间 设置太短可能会报错: org.apache.rocketmq.remoting.exception.RemotingTooMuchRequestException: sendDefaultImpl call timeout
        // TODO: 2024/7/20 设置生产者组
        producer.setProducerGroup("bewater_sync_producer_group");
        // TODO: 2024/7/20 启动生产者
        producer.start();

        // TODO: 2024/7/20 同步发送一百条消息
        for (int i = 0; i < 100; i++) {
            Message message = new Message("sync_topic","sync_tag",("hi"+i).getBytes());
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        // TODO: 2024/7/20 关闭生产者
        producer.shutdown();


    }




}
