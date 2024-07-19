package com.sz.bewater.practice.study.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

public class KafkaConsumerTest {

    public static void main(String[] args) {
        // TODO: 生产者的配置
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        // TODO: 消费者消费消息就需要反序列化
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // TODO: 需要将自己归为一个消费者组
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG,"bewater_group");
        // TODO: 创建生产者
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(configMap);
        // TODO: 消费者订阅topic 可以订阅多个
        consumer.subscribe(Collections.singleton("bewater"));
        // TODO: 消费者拉取消息 拉取多条  设置超时时间
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(item -> {
                System.out.println("成功消费了消息----------------------->"+item);
            });
        }


    }
}
