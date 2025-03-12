package com.sz.bewater.practice.interview.framework.spring.lifecycle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.sz.bewater.practice.interview.framework.spring.lifecycle.spring")
@EnableTransactionManagement // 显式启用事务管理 SpringBoot不需要开发手动加该注解由于自动配置 只需要引入starter-jdbc 然后配置数据库连接即可  下面的事务管理器也不需要自己配置
public class MySpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
        dataSource.setUsername("root");
        dataSource.setPassword("07121128");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) { //配置事务管理器
        return new DataSourceTransactionManager(dataSource);
    }
}
