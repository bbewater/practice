package com.sz.bewater.practice.study.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class OrderProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // TODO: 2024/7/20 rocketmq实现顺序消息   全局有序(一个队列)  分区有序(消息的queue选择器)
        DefaultMQProducer producer = new DefaultMQProducer("bewater_producer_group");
        producer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 要想实现全局有序  设置该topic下队列为一个
//        producer.setDefaultTopicQueueNums(1);
        producer.start();


        for (int i = 0; i < 100; i++) {
            Message message = new Message("order_topic", "order_tag", ("hi" + i).getBytes());
            String OrderId = "Order_Bewater_001";
            message.setKeys(OrderId);
            producer.send(message, new MessageQueueSelector() {

                //queue的选择算法  send方法中的第三个参数=select方法里的第三个参数Object arg
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    String orderId = (String) arg;
                    int hash = orderId.hashCode();
                    return mqs.get(hash % mqs.size());
                }
            },OrderId);
        }
        producer.shutdown();


    }
}
