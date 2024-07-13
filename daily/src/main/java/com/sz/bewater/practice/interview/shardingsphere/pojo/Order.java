package com.sz.bewater.practice.interview.shardingsphere.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@TableName("t_order")
@Data
@Accessors(chain = true)
public class Order {
    @TableId(type = IdType.AUTO)  //当配置了shardingsphere的分布式序列 就用shardingsphere的 没用配置就依赖数据库的自增主键
//    @TableId(type = IdType.ASSIGN_ID)
    private Long id;    //使用shardingsphere的分布式序列生成算法 也就是雪花算法
    private Long userId;
    private String orderNo;
    private BigDecimal amount;
}
