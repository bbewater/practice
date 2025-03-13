package com.sz.bewater.practice.interview.framework.spring.lifecycle.config;

import com.sz.bewater.practice.interview.basic.factory.factoryMethod.Factory;
import com.sz.bewater.practice.interview.framework.spring.factoryBean.MyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.sz.bewater.practice.interview.framework.spring")
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

    //FactoryBean通常需要交由Spring管理，但注册方式可以是显式配置（如@Bean），
    // 此时不需要在类上使用@Component，且能更好地控制实例化和依赖注入。
    @Bean
    public MyFactoryBean myFactoryBean() {
        MyFactoryBean myFactoryBean = new MyFactoryBean("user");
        return myFactoryBean;
    }
}
