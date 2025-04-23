package com.sz.bewater.practice.study.xxljob_batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.context.annotation.Configuration;
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
public class CustomStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        // 可选：记录 Step 开始执行
        log.info("Step {} 开始执行", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus() == BatchStatus.FAILED) {
            log.error("Step {} 执行失败，exitCode = {}, exitDesc = {}",
                    stepExecution.getStepName(),
                    stepExecution.getExitStatus().getExitCode(),
                    stepExecution.getExitStatus().getExitDescription());
        } else {
            log.info("Step {} 执行成功", stepExecution.getStepName());
        }
        return stepExecution.getExitStatus(); // ✅ 关键：返回原始状态
    }
}
