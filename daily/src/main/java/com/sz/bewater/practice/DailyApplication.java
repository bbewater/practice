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
}