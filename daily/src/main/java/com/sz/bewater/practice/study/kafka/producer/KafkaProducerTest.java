package com.sz.bewater.practice.study.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;

public class KafkaProducerTest {

    public static void main(String[] args) {
        // TODO: kafka生产者的配置信息
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        // TODO: 因为kafka传递消息是通过网络来进行传递的 所以需要进行序列化
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // TODO 创建kafka的生产者
        KafkaProducer<String,String> producer = new KafkaProducer(configMap);
        // TODO: 创建消息record  一次发送多条
        for (int i = 1; i <= 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("bewater","key"+i,"value");
            // TODO: 发送消息
            producer.send(record);
        }
        // TODO:  关闭生产者
        producer.close();




    }
}
