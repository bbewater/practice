package com.sz.bewater.practice;

import com.sz.bewater.practice.study.drools.House;
import com.sz.bewater.practice.study.drools.User;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/17
 */
@SpringBootTest
public class DroolsTest {
    @Autowired
    private KieContainer kieContainer;

    /**
     * drools非常快，Rete 网络可以一次插入多条数据批量匹配多个规则
     * 比如你现在一次 insert 10000 条 House 对象，Drools 可以一下子并行匹配多个规则、生成匹配结果，
     * 非常适合大批量数据匹配。
     *
     * 每个 kieSession匹配是单线程的
     * 虽然是单线程，但：
     *
     * Drools 通过预编译的 Rete 网络 把规则条件结构化；
     * 当数据一条条插入进去，Rete 网络能高效地复用匹配结果；
     * 所以比你手动 if-else + for 循环一条条数据判断要快得多。
     * 你可以理解为：“单线程，高效批量判断”。
     *
     * 如何高效并行匹配?
     * 使用时可以把数据分片，然后开多个线程，每个线程独立创建一个 KieSession 来并行执行。
     */

    @Test
    public void testRule(){
        List<House> houseList = new ArrayList<>();
        House house1 = new House(1, "..ss", "苏州", 8);
        House house2 = new House(2, "机房 2", "苏州", 20);
        House house3 = new House(3, "机房 3", "上海", 20);
        House house4 = new House(4, "机房 4", "南京", 50);
        House house5 = new House(5, "机房 00000000000000000000000000000000000", "洛杉矶", 5);
        houseList.add(house1);
        houseList.add(house2);
        houseList.add(house3);
        houseList.add(house4);
        houseList.add(house5);
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "bewater", "17768025565");
        User user2 = new User(2, "qiyana", "1776802556511");
        User user3 = new User(3, " ", "17768025565");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        for (int i = 0; i < 2; i++) {
            KieSession kieSession = kieContainer.newKieSession();
            if (i == 0){
                kieSession.getAgenda().getAgendaGroup("house-check").setFocus();
                //一次性 insert 全量数据，再 fireAllRules 不要 insert 一条就去fireAllRules 这样会很慢
                houseList.forEach(kieSession::insert);
            }else {
                kieSession.getAgenda().getAgendaGroup("user-check").setFocus();
                userList.forEach(kieSession::insert);
            }
            Map<String, List<Integer>> ruleHitMap = new HashMap<>();
            kieSession.setGlobal("ruleHitMap", ruleHitMap);
            kieSession.fireAllRules();
            kieSession.dispose(); //销毁会话
            ruleHitMap.forEach((k,v) -> System.out.println(k + ":" + v));
        }


    }
}
