package com.sz.bewater.practice.study.drools;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/17
 */
@Data
@AllArgsConstructor
public class House {
    private Integer id;
    private String houseName;
    private String houseAddress;
    private Integer hasIpCount;
}
