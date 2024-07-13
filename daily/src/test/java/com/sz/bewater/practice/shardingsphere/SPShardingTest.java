package com.sz.bewater.practice.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sz.bewater.practice.interview.shardingsphere.mapper.OrderMapper;
import com.sz.bewater.practice.interview.shardingsphere.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class SPShardingTest {
    @Autowired
    private OrderMapper orderMapper;


    /**
     * 水平分片
     *             database-strategy:    ## 分库策略，缺省表示使用默认分库策略，以下的分片策略只能选其一
     *               standard:       # 用于单分片键的标准分片场景
     *                 sharding-column: user_id   # 分片列名称
     *                 sharding-algorithm-name: myMod # 分片算法名称
     *         sharding-algorithms:
     *           myMod:
     *             type: MOD   #取模分片算法
     *             props:
     *               sharding-count: 4   #分片数量
     *  分库策略  我这里分片列为userId  分片数量为4  然后分片算法是取模  那就是userId % 4
     */
    @Test
    public void testSPSharding(){
        Order order = new Order().setUserId(8L).setOrderNo("jd0001").setAmount(new BigDecimal(55.55));
        orderMapper.insert(order);


    }

    @Test
    public void testSPShardingSelect(){
//        orderMapper.selectList(null);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",4L);
        orderMapper.selectList(wrapper);


    }
}
