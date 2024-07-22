package com.sz.bewater.practice.study.rocketmq.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class FilterProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {

        // TODO: 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("bewater_producer_group");
        // TODO: 配置namesrv地址  配置一个也行(多个namesrv里面的数据是一致的)  配置多个 更好 以备一个时不可用
        producer.setNamesrvAddr("rocketmqOS3:9876");
        // TODO: 2024/7/20 启动生产者
        producer.start();

        // TODO: 2024/7/20 单向发送一百条消息
        String[] tags = {"tagA","tagB","tagC"};
        for (int i = 0; i < 3; i++) {
            Message message = new Message("filter_topic",tags[i],("hi"+i).getBytes());
            if (i == 2){
                // TODO: 2024/7/21 message设置用户属性  以便后续使用sql来进行过滤消息 
                message.putUserProperty("age","6");
            }
            producer.sendOneway(message);   //返回值为void
        }

        // TODO: 2024/7/20 关闭生产者
        producer.shutdown();






    }
}
