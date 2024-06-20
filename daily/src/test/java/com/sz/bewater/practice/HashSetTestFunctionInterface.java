package com.sz.bewater.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class HashSetTestFunctionInterface {


    @Test
    public void test(){
        Set<String> name = new HashSet();

        List<String> list = new ArrayList<>();
//        list.add("tom");
        list.add("tom");
        list.add("jerry");
        for (String item : list) {
            name.add(item);
            int size = name.size();
            if (name.size() == size) {
                System.out.println("重复");
            }
        }



    }
}
