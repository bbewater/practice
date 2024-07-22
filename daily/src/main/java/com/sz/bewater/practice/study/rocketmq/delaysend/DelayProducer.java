package com.sz.bewater.practice.study.rocketmq.delaysend;

import io.vertx.core.impl.cpu.CpuCoreSensor;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class DelayProducer {
    // TODO: 2024/7/20 实现延时消费
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("bewater_producer_group");
        producer.setNamesrvAddr("rocketmqOS3:9876");
        producer.start();

        Message message = new Message("delay_topic", "delay_tag", "hello".getBytes());
        message.setDelayTimeLevel(3);
        SendResult sendResult = producer.send(message);
        producer.shutdown();

    }
}
