package com.sz.bewater.practice.shardingsphere;

import com.sz.bewater.practice.interview.shardingsphere.mapper.UserMapper;
import com.sz.bewater.practice.interview.shardingsphere.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReadWriteTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("奇亚娜");
        userMapper.insert(user);
    }



//    为了保证主从事务一致性  加了@Transactional后  读写均在主库
//    在junit测试中  加了@Transactional注解  就会回滚  rolled back transaction for test
    @Transactional
    @Test
    public void testTran() {
        User user = new User();
        user.setUsername("回滚");
        userMapper.insert(user);
        userMapper.selectList(null);
//        Actual SQL: master ::: SELECT  id,username  FROM t_user

    }

    @Test
    public void testLb() {
        userMapper.selectList(null);
        userMapper.selectList(null);
        userMapper.selectList(null);
        userMapper.selectList(null);
//        Actual SQL: slave1 ::: SELECT  id,username  FROM t_user
//        Actual SQL: slave2 ::: SELECT  id,username  FROM t_user

    }


}
