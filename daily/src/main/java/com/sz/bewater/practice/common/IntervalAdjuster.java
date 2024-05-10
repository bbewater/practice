package com.sz.bewater.practice.common;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class IntervalAdjuster {
//    List<IntervalNew> intervals = new ArrayList<>();
//
//    public void addInterval(int start, int end) {
//        List<IntervalNew> newIntervals = new ArrayList<>();
//        boolean inserted = false;
//
//        // 遍历现有区间，合并新区间和重叠的"N"状态区间，同时处理与"Y"状态区间的重叠
//        for (IntervalNew interval : intervals) {
//            if (interval.end < start || interval.start > end) {
//                // 与新区间无重叠
//                newIntervals.add(interval);
//            } else if ("N".equals(interval.status)) {
//                // 与新区间有重叠，且当前区间状态为"N"，合并区间
//                start = Math.min(start, interval.start);
//                end = Math.max(end, interval.end);
//            } else if ("Y".equals(interval.status)) {
//                // 处理与"Y"状态区间的重叠
//                if (start < interval.start) {
//                    newIntervals.add(new IntervalNew(start, interval.start - 1, "Y"));
//                    inserted = true;
//                }
//                newIntervals.add(interval);
//                if (end <= interval.end) {
//                    // 新区间完全被当前"Y"区间覆盖
//                    start = end + 1;
//                } else {
//                    // 新区间的一部分仍未被覆盖
//                    start = interval.end + 1;
//                }
//            }
//        }
//
//        // 如果新区间的一部分未被"Y"区间完全覆盖，则添加剩余部分
//        if (!inserted || start <= end) {
//            newIntervals.add(new IntervalNew(start, end, "Y"));
//        }
//
//        // 对合并后的区间进行排序
//        intervals = newIntervals.stream()
//                .sorted((i1, i2) -> Integer.compare(i1.start, i2.start))
//                .collect(Collectors.toList());
//    }
//
//    public void printIntervals() {
//        intervals.forEach(System.out::println);
//    }
//
//    public static void main(String[] args) {
//        IntervalAdjuster manager = new IntervalAdjuster();
//        // 初始化时添加两个"Y"状态的区间
//        manager.intervals.add(new IntervalNew(7, 9, "N"));
//        manager.intervals.add(new IntervalNew(12, 15, "N"));
//        // 添加一个新区间，预期合并和调整后的区间会按顺序打印
//        manager.addInterval(10, 17);
//        manager.printIntervals();
//    }

    // 修改addInterval方法来处理两个区间集合
    public List<IntervalNew> addInterval(List<IntervalNew> dbList, List<IntervalNew> importList) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 对dbList和importList进行排序
        dbList.sort(Comparator.comparingInt(i -> i.start));
        importList.sort(Comparator.comparingInt(i -> i.start));

        for (IntervalNew importInterval : importList) {
            List<IntervalNew> tempDbList = new ArrayList<>();
            int start = importInterval.start;
            int end = importInterval.end;
            boolean inserted = false;

            for (IntervalNew dbInterval : dbList) {
                if (dbInterval.end < start || dbInterval.start > end) {
                    tempDbList.add(dbInterval);
                } else if ("N".equals(dbInterval.status)) {
                    // 合并"N"状态的区间
                    start = Math.min(start, dbInterval.start);
                    end = Math.max(end, dbInterval.end);
                } else if ("Y".equals(dbInterval.status)) {
                    // 处理与"Y"状态区间的重叠
                    if (start < dbInterval.start) {
                        tempDbList.add(new IntervalNew(start, dbInterval.start - 1, "Y"));
                        inserted = true;
                    }
                    tempDbList.add(dbInterval);
                    if (end <= dbInterval.end) {
                        start = end + 1;
                    } else {
                        start = dbInterval.end + 1;
                    }
                }
            }

            if (!inserted || start <= end) {
                tempDbList.add(new IntervalNew(start, end, "Y"));
            }

            // 更新dbList为当前合并后的列表
            dbList = tempDbList.stream()
                    .sorted(Comparator.comparingInt(i -> i.start))
                    .collect(Collectors.toList());
        }
        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getTotalTimeMillis() + "ms");
        return dbList;
    }

    public List<IntervalNew> addIntervalNew(List<IntervalNew> dbList, List<IntervalNew> importList) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 对dbList和importList进行排序
        dbList.sort(Comparator.comparingInt(i -> i.start));
        importList.sort(Comparator.comparingInt(i -> i.start));

        for (IntervalNew importInterval : importList) {
            List<IntervalNew> tempDbList = new ArrayList<>();
            int start = importInterval.start;
            int end = importInterval.end;
            Iterator<IntervalNew> iterator = dbList.iterator();
            while (iterator.hasNext()) {
                IntervalNew dbInterval = iterator.next();
                if (dbInterval.start == start && dbInterval.end == end){
                    dbInterval.count++;
                }
                if (dbInterval.end < start || dbInterval.start > end) {
                    tempDbList.add(dbInterval);
                } else if ("N".equals(dbInterval.status)) {
                    // 如果"N"状态的区间与导入区间存在交集，则去除交集，保留剩余的"N"状态区间
                    if (dbInterval.start < start) {
                        tempDbList.add(new IntervalNew(dbInterval.start, Math.min(start, dbInterval.end) - 1, "N"));
                    }
                    if (dbInterval.end > end) {
                        tempDbList.add(new IntervalNew(Math.max(end + 1, dbInterval.start), dbInterval.end, "N"));
                    }
                } else if ("Y".equals(dbInterval.status)) {
                    // 处理与"Y"状态区间的重叠
                    if (start < dbInterval.start) {
                        tempDbList.add(new IntervalNew(start, dbInterval.start - 1, "Y"));
                    }
                    tempDbList.add(dbInterval);
                    if (end <= dbInterval.end) {
                        start = end + 1;
                    } else {
                        start = dbInterval.end + 1;
                    }
                }

            }

            if (start <= end) {
                tempDbList.add(new IntervalNew(start, end, "Y"));
            }

            // 更新dbList为当前合并后的列表
            dbList = tempDbList.stream()
                    .sorted(Comparator.comparingInt(i -> i.start))
                    .collect(Collectors.toList());
        }
        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getTotalTimeMillis() + "ms");
        return dbList;
    }












    public static void main(String[] args) {
        IntervalAdjuster manager = new IntervalAdjuster();
        // 初始化dbList
        List<IntervalNew> dbList = new ArrayList<>();
        dbList.add(new IntervalNew(51, 55, "N"));

        // 初始化importList
        List<IntervalNew> importList = new ArrayList<>();
        importList.add(new IntervalNew(52, 52));
        importList.add(new IntervalNew(54, 60));
//        importList.add(new IntervalNew(3, 4));
//        importList.add(new IntervalNew(9, 9));
//        importList.add(new IntervalNew(10, 11));
//        importList.add(new IntervalNew(12, 13));
//        importList.add(new IntervalNew(14, 16));
//        importList.add(new IntervalNew(17, 18));
//        importList.add(new IntervalNew(19, 22));
//        importList.add(new IntervalNew(23, 25));

        // 合并区间并更新dbList
        dbList = manager.addIntervalNew(dbList, importList);

        // 打印更新后的dbList
        dbList.forEach(System.out::println);
    }
}
