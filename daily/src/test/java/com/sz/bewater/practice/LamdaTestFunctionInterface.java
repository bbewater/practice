package com.sz.bewater.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class LamdaTestFunctionInterface {



    @Test
    public void test(){
        new Thread(() -> System.out.println(""));
    }
}
