package com.sz.bewater.practice.interview.framework.spring.eventPublish;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("receive event : "+event.getMessage());
    }
}
