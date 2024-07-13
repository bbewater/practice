package com.sz.bewater.practice.interview.shardingsphere.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_seller")
@Data
@Accessors(chain = true)
public class Seller {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String sellerName;
    private String sellerAddr;
}
