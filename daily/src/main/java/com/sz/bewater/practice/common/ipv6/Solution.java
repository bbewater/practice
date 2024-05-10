package com.sz.bewater.practice.common.ipv6;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

public class Solution {

    public static List<Interval> insertAndSplit(List<Interval> list1, List<Interval> list2) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Interval> result = new ArrayList<>();
        int i = 0; // source的位置

        for (Interval dest : list2) {
            boolean isDestAdded = false;

            while (i < list1.size()) {
                Interval source = list1.get(i);

                // 1. dest完全在source左侧，无交叉
                if (dest.end < source.start) {
                    result.add(dest);
                    isDestAdded = true;
                    result.add(source);
                    i++;
                    break;
                }
                // 7. dest完全在source右侧，无交叉
                else if (dest.start > source.end) {
                    result.add(source);
                    i++;
                    if (i == list1.size()) {
                        result.add(dest);
                        isDestAdded = true;
                    }
                }
                // 3. dest与source完全重合
                else if (dest.start == source.start && dest.end == source.end) {
                    result.add(source);
                    i++;
                    isDestAdded = true;
                    break;
                }
                // 4. dest被source完全包含
                else if (dest.start >= source.start && dest.end <= source.end) {
                    result.add(dest);
                    //4.1 左包含
                    if (dest.start == source.start && dest.end < source.end){
                        list1.set(i, new Interval(dest.end + 1, source.end));
                        //4.2 右包含
                    } else if (dest.start > source.start && dest.end == source.end) {
                        result.add(new Interval(source.start, dest.start-1));
                        i++;
                        //4.3 中间包含
                    }else {
                        result.add(new Interval(source.start, dest.start-1));
                        list1.set(i, new Interval(dest.end + 1, source.end));
                    }
                    isDestAdded = true;
                    break;
                }
                // 2. dest与source左侧交叉
                else if (dest.start < source.start && dest.end < source.end) {
                    result.add(new Interval(dest.start, source.start - 1));
                    result.add(new Interval(source.start, dest.end));
                    list1.set(i, new Interval(dest.end + 1, source.end));
                    isDestAdded = true;
                    break;
                }
                // 5. dest与source右侧交叉
                else if (dest.start > source.start) {
                    result.add(new Interval(source.start, dest.start - 1));
                    result.add(new Interval(dest.start, source.end));
                    dest = new Interval(source.end+1, dest.end); //调整dest的值为右侧不交叉部分
                    i++;
                }
                // 6.dest完全包含原区间
                else {
                    // 添加source作为交叉部分
                    result.add(source);
                    //6.1 左侧包含
                    if (dest.start == source.start){
                        // 更新dest为其右侧未交叉部分，并继续比较
                        dest = new Interval(source.end + 1, dest.end);
                        i++; // 移动到下一个source继续处理
                    //6.2 右侧包含
                    }else if (dest.end == source.end){
                        // 添加dest左侧未交叉部分
                        result.add(new Interval(dest.start, source.start - 1));
                        i++;
                        isDestAdded = true;
                        break;
                    //6.3 中间包含
                    }else {
                        // 添加dest左侧未交叉部分
                        result.add(new Interval(dest.start, source.start - 1));
                        // 更新dest为其右侧未交叉部分，并继续比较
                        dest = new Interval(source.end + 1, dest.end);
                        i++; // 移动到下一个source继续处理
                    }

                }
            }

            if (!isDestAdded) {
                result.add(dest);
            }
        }

        // 添加还剩下的source
        while (i < list1.size()) {
            result.add(list1.get(i++));
        }

        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getTotalTimeMillis() + "ms");
        return result;
    }


    public static void main(String[] args) {
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(2, 6));
        list1.add(new Interval(8, 12));
//        list1.add(new Interval(6, 6));
         list1.add(new Interval(20, 25));

        List<Interval> list2 = new ArrayList<>();
//        list2.add(new Interval(4, 9));
//        list2.add(new Interval(16, 20));
//         list2.add(new Interval(15, 19));
        list2.add(new Interval(4, 9));
        list2.add(new Interval(13, 13));

        List<Interval> result = insertAndSplit(list1, list2);
        result.sort(Comparator.comparingInt(item -> item.start));
        for (Interval interval : result) {
            System.out.println(interval);
        }
    }
}
