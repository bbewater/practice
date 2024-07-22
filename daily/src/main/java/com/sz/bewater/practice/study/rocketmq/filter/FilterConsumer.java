package com.sz.bewater.practice.study.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class FilterConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("bewater_consumer_group");
        // TODO: 2024/7/20 配置namesrv
        consumer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 配置消费者订阅的主题与tag  配置tag过滤
//        consumer.subscribe("filter_topic","tagA || tagB");
        // TODO: 2024/7/20 配置消费者订阅的主题与tag  配置sql过滤  注意需要提前在配置文件broker.conf配置上开启sql过滤 enablePropertyFilter = true
        consumer.subscribe("filter_topic", MessageSelector.bySql("name is not null and name between 0 and 10"));
        // TODO: 2024/7/20 配置消费者从哪开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // TODO: 2024/7/20 配置消费者消费类型 默认是集群消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(Thread.currentThread().getName()+"-- msg is =="+msg);

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("消费者"+consumer.getClientIP()+"启动");






    }
}
