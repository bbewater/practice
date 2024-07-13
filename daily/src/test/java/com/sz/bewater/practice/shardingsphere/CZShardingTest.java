package com.sz.bewater.practice.shardingsphere;

import com.sz.bewater.practice.interview.shardingsphere.mapper.OrderMapper;
import com.sz.bewater.practice.interview.shardingsphere.mapper.UserMapper;
import com.sz.bewater.practice.interview.shardingsphere.pojo.Order;
import com.sz.bewater.practice.interview.shardingsphere.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CZShardingTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void testUserAndOrderInsert(){
        User user = new User();
        user.setUsername("kate");
        userMapper.insert(user);
        Order order = new Order();
        order.setOrderNo("jd004").setUserId(user.getId()).setAmount(new BigDecimal(666.66));
        orderMapper.insert(order);


    }
}
