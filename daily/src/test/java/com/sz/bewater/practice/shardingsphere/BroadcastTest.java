package com.sz.bewater.practice.shardingsphere;

import com.sz.bewater.practice.interview.shardingsphere.mapper.SellerMapper;
import com.sz.bewater.practice.interview.shardingsphere.pojo.Seller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BroadcastTest {


    @Autowired
    private SellerMapper sellerMapper;


    @Test
    public void testBroadcast(){
        List<Seller> sellers = sellerMapper.selectList(null);
        sellers.forEach(System.out::println);

    }
}
