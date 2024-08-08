package com.sz.bewater.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class,args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context){
//        return args -> {
//            MyEventPublish publish = context.getBean(MyEventPublish.class);
//            publish.publish("发布了一个事件");
//        };
//    }

//    CommandLineRunner 是 Spring Boot 中的一个接口，它的主要作用是在应用启动完成后执行一段代码。实现 CommandLineRunner 接口并重写其 run 方法，可以在 Spring Boot 应用启动完成后立即执行自定义逻辑。通常用于在应用启动时进行一些初始化操作。
}