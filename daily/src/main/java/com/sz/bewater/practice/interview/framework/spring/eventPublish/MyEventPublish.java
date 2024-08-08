package com.sz.bewater.practice.interview.framework.spring.eventPublish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublish {
    @Autowired
    private ApplicationEventPublisher publisher;


    public void publish(String message){
        MyApplicationEvent event = new MyApplicationEvent(this, message);
        publisher.publishEvent(event);
    }

}
