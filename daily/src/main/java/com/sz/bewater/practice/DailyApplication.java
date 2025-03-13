package com.sz.bewater.practice;

import com.sz.bewater.practice.interview.framework.spring.eventPublish.MyApplicationEvent;
import com.sz.bewater.practice.interview.framework.spring.eventPublish.MyEventPublish;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(DailyApplication.class, args);
//        context.publishEvent(new MyApplicationEvent(new DailyApplication(),"这是一个事件"));

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return args -> {
            MyEventPublish publish = context.getBean(MyEventPublish.class);
            publish.publish("发布了一个事件");
        };
    }

//    CommandLineRunner 是 Spring Boot 中的一个接口，它的主要作用是在应用启动完成后执行一段代码。实现 CommandLineRunner 接口并重写其 run 方法，可以在 Spring Boot 应用启动完成后立即执行自定义逻辑。通常用于在应用启动时进行一些初始化操作。
}