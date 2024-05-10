package com.sz.bewater.practice.common;

import java.util.ArrayList;
import java.util.List;

public class IntervalManager {
    List<Interval> intervals;

    public IntervalManager() {
        intervals = new ArrayList<>();
    }

    public void addInterval(Interval newInterval) {
        List<Interval> tempIntervals = new ArrayList<>();
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        boolean isCovered = false; // 新增变量，用于标记新区间是否已被覆盖

        // 遍历现有区间，检查新区间是否被完全覆盖
        for (Interval current : intervals) {
            if (newStart >= current.start && newEnd <= current.end) {
                isCovered = true; // 新区间被完全覆盖
                break;
            }
        }

        // 如果新区间已被完全覆盖，则不进行任何操作，直接返回
        if (isCovered) {
            return;
        }

        // 标记是否已经添加新区间的部分
        boolean added = false;

        for (Interval current : intervals) {
            if (newEnd < current.start && !added) {
                tempIntervals.add(new Interval(newStart, newEnd));
                added = true;
            }

            if (newEnd < current.start || newStart > current.end) {
                tempIntervals.add(current);
            } else {
                if (newStart < current.start && !added) {
                    tempIntervals.add(new Interval(newStart, current.start - 1));
                    added = true;
                }
                tempIntervals.add(current);
                if (newEnd > current.end) {
                    newStart = current.end + 1;
                    added = false;
                }
            }
        }

// 添加新区间的剩余部分，如果存在且未被添加
        if (!added) {
            // 在尝试添加之前，再次检查新区间是否被覆盖或与现有区间重叠
            boolean stillValid = true;
            for (Interval current : intervals) {
                if (newStart >= current.start && newStart <= current.end) {
                    stillValid = false; // 新的起点仍然位于一个现有区间内，不应添加
                    break;
                }
            }
            if (stillValid && newStart <= newEnd) {
                tempIntervals.add(new Interval(newStart, newEnd));
            }
        }


        // 更新区间列表
        intervals = tempIntervals;
    }





    public List<Interval> getIntervals() {
        return intervals;
    }

    // 可以添加一个方法来打印当前所有区间，方便验证
    public void printIntervals() {
        for (Interval interval : intervals) {
            System.out.println("(" + interval.start + "," + interval.end + ")");
        }
    }
}
