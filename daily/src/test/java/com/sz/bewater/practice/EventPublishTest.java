package com.sz.bewater.practice;

import com.sz.bewater.practice.interview.framework.spring.eventPublish.MyEventPublish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class EventPublishTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testPublish(){
        MyEventPublish publish = context.getBean(MyEventPublish.class);
        publish.publish("我发布了一个事件");
    }
}
