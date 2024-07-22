package com.sz.bewater.practice.study.rocketmq.oneway;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {



        // TODO: 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer();
        // TODO: 配置namesrv地址  配置一个也行(多个namesrv里面的数据是一致的)  配置多个 更好 以备一个时不可用
        producer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 设置生产者组
        producer.setProducerGroup("bewater_oneway_producer_group");
        // TODO: 2024/7/20 启动生产者
        producer.start();

        // TODO: 2024/7/20 单向发送一百条消息
        for (int i = 0; i < 100; i++) {
            Message message = new Message("oneway_topic","oneway_tag",("hi"+i).getBytes());
            producer.sendOneway(message);   //返回值为void
        }

        // TODO: 2024/7/20 关闭生产者
        producer.shutdown();
    }
}
