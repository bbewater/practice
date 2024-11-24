package com.sz.bewater.practice.study.rocketmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class OrderConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("bewater_consumer_group");
        // TODO: 2024/7/20 配置namesrv
        consumer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 配置消费者订阅的主题与tage
        consumer.subscribe("order_topic","*");
        // TODO: 2024/7/20 配置消费者从哪开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // TODO: 2024/7/20 配置消费者消费类型 默认是集群消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setMessageListener((MessageListenerOrderly) (msgs,context) -> {
            msgs.forEach(item -> System.out.println(new String(item.getBody())));
            return ConsumeOrderlyStatus.SUCCESS;
        });
//        consumer.setMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                msgs.forEach(item -> System.out.println(new String(item.getBody())));
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });
        consumer.start();
        System.out.println("生产者"+consumer.getClientIP()+"启动");





    }
}
