package com.sz.bewater.practice.study.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

public class MyTransactionListener implements TransactionListener {
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // TODO: 2024/7/21 rocketmq 收到事务消息 回调此函数执行本地事务  用tagA模拟成功  tagB模式回滚  tagC模拟unknown mq会隔段时间走下面回查回调
        // TODO: 2024/7/21 只有执行完本地事务 并成功回复MQ成功  事务消息才能被消费者成功消费 如果回复 ROLLBACK_MESSAGE则事务消息会被删除 如果回复UNKNOW MQ会一直回查
        System.out.println("---rocketmq 收到事务消息 回调此函数执行本地事务---");
        if (msg.getTags().equals("TagA")){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        if (msg.getTags().equals("TagB")){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        if (msg.getTags().equals("TagC")){
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        // TODO: 2024/7/21 rocketmq的回查回调  当未收到生产者执行本地事务的结果(超时)  或者是  生产者执行本地事务回复UNKNOWN 的时候 MQ会隔段时间来回调这个函数
        System.out.println("---rocketmq的回查回调---");
        if (msg.getTags().equals("TagC")){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return null;
    }
}
