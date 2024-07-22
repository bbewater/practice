package com.sz.bewater.practice.study.rocketmq.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TransactionProducer {


    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("bewater_producer_group");
        producer.setNamesrvAddr("rocketmqOS3:9876");
        ExecutorService pool = new ThreadPoolExecutor(2,
                5,
                5000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100),
                r -> new Thread(r, "transaction_thread"));
        producer.setExecutorService(pool);  //设置事务消息生产者发送线程池
        producer.setTransactionListener(new MyTransactionListener());   //设置事务消息监听器
        producer.start();

        String[] tags = {"TagA","TagB","TagC"};
        for (int i = 0; i < 3; i++) {
            Message message = new Message("tx_topic", tags[i], ("hi bewater").getBytes());
            // TODO: 2024/7/21 第二个参数为执行本地事务需要传递的参数
            TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message, null);
            System.out.println("transactionSendResult is =="+transactionSendResult);
        }



    }
}
