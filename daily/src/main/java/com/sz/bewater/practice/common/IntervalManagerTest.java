package com.sz.bewater.practice.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IntervalManagerTest {

    public static void main(String[] args) {
        IntervalManager manager = new IntervalManager();

        // 初始区间 (2,4) 和 (6,9)
//        manager.addInterval(new Interval(2, 4));
//        manager.addInterval(new Interval(6, 9));
//        manager.addInterval(new Interval(12, 15));
        List<Interval> intervals = new ArrayList<>();
        Interval interval1 = new Interval(12,15,"Y");
//        Interval interval5 = new Interval(16,16);
//        Interval interval4 = new Interval(10,11);
//        Interval interval2 = new Interval(2,4);
        Interval interval3 = new Interval(6,9,"Y");

        intervals.add(interval1);
//        intervals.add(interval2);
        intervals.add(interval3);
//        intervals.add(interval4);
//        intervals.add(interval5);
        manager.intervals = intervals.stream().sorted(Comparator.comparing(Interval::getStart)).collect(Collectors.toList());
//        System.out.println("初始区间:");
//        manager.printIntervals();
//
//        // 场景 1: 插入 (2,3) 和 (6,8)，预期无变化
//        System.out.println("插入 (2,3) 和 (6,8)，预期无变化:");
//        manager.addInterval(new Interval(2, 3));
//        manager.addInterval(new Interval(6, 8));
//        manager.printIntervals();
//
//        // 场景 2: 插入 (10,12)，预期添加此区间
//        System.out.println("插入 (10,12)，预期添加此区间:");
//        manager.addInterval(new Interval(10, 12));
//        manager.printIntervals();

        // 场景 3: 插入 (1,10)，预期添加 (1,1) 和 (5,5) 和 (10,10)
//        System.out.println("插入 (1,10)，预期添加 (1,1) 和 (5,5) 和 (10,10):");
//        manager.addInterval(new Interval(1, 10));
//        manager.printIntervals();

//        // 场景 4: 插入 (3,8)，预期添加 (5,5)
//        System.out.println("插入 (3,8)，预期添加 (5,5):");
//        manager.addInterval(new Interval(3, 8));
//        manager.printIntervals();

//        System.out.println("插入 (1,20)，预期添加 (1,1)和5,5和10,11和16,20:");
        manager.addInterval(new Interval(7,7,"Y"));
        manager.printIntervals();

//        System.out.println("插入 (16,20)，预期添加 (16,20):");
//        manager.addInterval(new Interval(16,20));
//        manager.printIntervals();

//        System.out.println("插入 (8,10)，预期添加 (10,10):");
//        manager.addInterval(new Interval(8,10));
//        manager.printIntervals();

//        System.out.println("插入 (1,2)，预期添加 (1,1):");
//        manager.addInterval(new Interval(1,2));
//        manager.printIntervals();
    }
}
