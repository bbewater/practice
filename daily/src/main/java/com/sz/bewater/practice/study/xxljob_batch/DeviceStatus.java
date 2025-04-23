package com.sz.bewater.practice.study.xxljob_batch;

import lombok.Data;

import java.util.Date;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/22
 */
@Data
public class DeviceStatus {
    private Integer id;
    private String deviceId;
    private String status;
    private String recordTime;
}
