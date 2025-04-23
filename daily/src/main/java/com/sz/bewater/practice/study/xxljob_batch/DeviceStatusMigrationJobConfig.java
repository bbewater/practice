package com.sz.bewater.practice.study.xxljob_batch;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.sql.Timestamp;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/22
 */
@Configuration
@EnableBatchProcessing
public class DeviceStatusMigrationJobConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TaskExecutor batchTaskExecutor;
    @Autowired
    private JobLauncher jobLauncher;


    @XxlJob("deviceStatusMigrationJobHandler")
    public void executeJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                //timestamp 保证 Spring Batch 视为新的 JobInstance，否则同一个 JobName + JobParams 是不会重新执行的！
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        JobExecution run = jobLauncher.run(deviceStatusMigrationJob(), jobParameters);
        if (run.getStatus() == BatchStatus.COMPLETED) {

        }
    }

    /**
     * 主 Job，包含三个 Step，对应三个月的迁移 并发执行
     */
    @Bean
    public Job deviceStatusMigrationJob() {
        return jobBuilderFactory.get("parallelMigrationJob")
                .start(flow("202301", batchTaskExecutor))
                .split(batchTaskExecutor)
                .add(flow("202302", batchTaskExecutor), flow("202303", batchTaskExecutor))
                .end()
                .build();
        //这两个 flow 会和上面那个 flow（start 中的 flow）并行执行。即总共有三个 flow，分别处理 2023 年 1、2、3 月的数据，并发执行，互不干扰。
    }

    public Flow flow(String month, TaskExecutor taskExecutor) {
        return new FlowBuilder<SimpleFlow>("flow-" + month)
                .start(stepForMonth(month))
                .build();
    }



    /**
     * 构建指定月份的迁移 Step
     */
    public Step stepForMonth(String targetMonth) {
        return stepBuilderFactory.get("migrateStep_" + targetMonth)
                .<DeviceStatus, DeviceStatus>chunk(1000)
                .reader(itemReader(targetMonth))
                .writer(itemWriter(targetMonth))
                .faultTolerant()// 支持容错，允许重试
                .retryLimit(3)
                .retry(Exception.class)
                .skipLimit(100)
                .skip(Exception.class)
                .listener(CustomStepListener.class)
                .listener(CustomSkipListener.class)
                .build();
    }

    /**
     * ItemReader: 读取指定月份的数据
     */
    public ItemReader<DeviceStatus> itemReader(String targetMonth) {
        JdbcPagingItemReader<DeviceStatus> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setPageSize(1000);
        reader.setRowMapper(new BeanPropertyRowMapper<>(DeviceStatus.class));

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT *");
        queryProvider.setFromClause("FROM device_status");
        queryProvider.setWhereClause("WHERE DATE_FORMAT(record_time, '%Y%m') = '" + targetMonth + "'");
        queryProvider.setSortKey("id");

        try {
            reader.setQueryProvider(queryProvider.getObject());
        } catch (Exception e) {
            throw new RuntimeException("Failed to set query provider", e);
        }

        return reader;
    }

    /**
     * 数据处理逻辑，此处不修改原数据，直接 passthrough
     */
    @Bean
    public ItemProcessor<DeviceStatus, DeviceStatus> itemProcessor() {
        return item -> item;
    }
    /**
     * ItemWriter: 将数据写入对应分表
     * 使用 INSERT ... ON DUPLICATE KEY UPDATE 实现幂等写入
     */
    public ItemWriter<DeviceStatus> itemWriter(String targetMonth) {
        return new JdbcBatchItemWriterBuilder<DeviceStatus>()
                .dataSource(dataSource)
                .sql("INSERT INTO ping_history" + targetMonth +
                        " (id, device_id, status, record_time) VALUES (?, ?, ?, ?)" +
                        " ON DUPLICATE KEY UPDATE " +
                        " status = VALUES(status), record_time = VALUES(record_time)")
                .itemPreparedStatementSetter((item, ps) -> {
                    ps.setLong(1, item.getId());
                    ps.setString(2, item.getDeviceId());
                    ps.setString(3, item.getStatus());
                    ps.setTimestamp(4, Timestamp.valueOf(item.getRecordTime()));
                })
                .build();
    }
}
