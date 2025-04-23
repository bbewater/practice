package com.sz.bewater.practice.study.xxljob_batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/22
 */
@Component
@Slf4j
public class CustomSkipListener implements SkipListener<DeviceStatus, DeviceStatus> {

    @Override
    public void onSkipInWrite(DeviceStatus item, Throwable t) {
        // 记录到日志文件、数据库、Redis 等
        log.warn("Write 跳过记录: " + item + ", 原因: " + t.getMessage());
        saveToSkipTable(item, t);
    }

    @Override
    public void onSkipInProcess(DeviceStatus item, Throwable t) {
        log.warn("Process 跳过记录: " + item + ", 原因: " + t.getMessage());
        saveToSkipTable(item, t);
    }

    @Override
    public void onSkipInRead(Throwable t) {
        log.warn("Read 跳过记录, 原因: " + t.getMessage());
        // 如果需要保存 Reader 层的 skip 可补充
    }

    private void saveToSkipTable(DeviceStatus item, Throwable t) {
        // 你可以写入数据库、Kafka、文件、Elasticsearch 供后续重处理
    }
}

