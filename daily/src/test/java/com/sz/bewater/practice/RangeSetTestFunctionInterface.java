package com.sz.bewater.practice;


import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class RangeSetTestFunctionInterface {


    @Test
    public void test(){
        //RangeSet是一系列区间的集合
        RangeSet rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1,5)); //true 闭区间[1,5]
        System.out.println(rangeSet.contains(5));   //contains:是否包含该元素
        rangeSet.add(Range.closedOpen(1,6));// false closedOpen代表左闭右开区间[1,6)
        System.out.println(rangeSet.contains(1));
        rangeSet.remove(Range.closed(1,2));//(2,6)  remove方法 去掉重复的区间还剩下的区间
        Set<Range> ranges = rangeSet.asRanges();    //asRanges 用于循环迭代

        System.out.println(rangeSet.rangeContaining(5)); //(2,6) rangeContaining返回包含该元素的区间
        System.out.println(rangeSet.complement()); //(-∞,2],[6,+∞) 返回它的补集
        System.out.println(rangeSet.encloses(Range.closed(3,4))); //true encloses:是否完全包含另一个区间
        rangeSet.add(Range.closed(6,8)); //(2,6) [6,8]
        System.out.println(rangeSet.span()); //(2,8] 返回包含这些个区间的最小范围(相当于合并在了一起) 即整个范围集的补集将被移除。
        // 注意span操作后rangeSet里面就剩下一个了
        System.out.println(rangeSet.isEmpty()); //false  判空
        for (Range<Integer> range : ranges) {
            System.out.println(range);
        }



        RangeSet<Integer> mergeRangeSet = TreeRangeSet.create();
        mergeRangeSet.add(Range.closed(1, 5));
        mergeRangeSet.add(Range.closedOpen(3, 8));
        mergeRangeSet.add(Range.closedOpen(10, 12));

        System.out.println("Before merging: " + mergeRangeSet); //[1,8),[10,12) RangeSet在add的时候就自动完成了重复合并了

        // 合并相邻的范围  不需要了  不要手动合并
        mergeAdjacentRanges(mergeRangeSet);

        System.out.println("After merging: " + mergeRangeSet);

        RangeSet<Integer> treeRangeSet = TreeRangeSet.create();
        treeRangeSet.add(Range.closed(1, 5));
        treeRangeSet.add(Range.closed(7, 8));
        treeRangeSet.add(Range.closed(10, 12));
        System.out.println("complement---"+treeRangeSet.complement());

    }

    private static void mergeAdjacentRanges(RangeSet<Integer> rangeSet) {
        // 将范围集中的范围按照起始值进行排序
        List<Range<Integer>> ranges = Lists.newArrayList(rangeSet.asRanges()).stream().sorted(Comparator.comparing(Range::lowerEndpoint)).collect(Collectors.toList());

        // 遍历并合并相邻的范围
        for (int i = 0; i < ranges.size() - 1; i++) {
            Range<Integer> currentRange = ranges.get(i);
            Range<Integer> nextRange = ranges.get(i + 1);

            if (currentRange.isConnected(nextRange)) { //isConnected是否以下一个区间作为开始
                // 如果相邻，则合并范围
                Range<Integer> mergedRange = currentRange.span(nextRange); //span合并
                rangeSet.remove(currentRange);
                rangeSet.remove(nextRange);
                rangeSet.add(mergedRange);
            }
        }
    }
    private static void handleIntersection(RangeSet<Integer> originalRangeSet, RangeSet<Integer> resultRangeSet, Range<Integer> newRange) {
        for (Range<Integer> range : originalRangeSet.asRanges()) {
            if (range.isConnected(newRange)) {
                // Add the complement of the intersection to the resultRangeSet
                if (range.lowerEndpoint() < newRange.lowerEndpoint()) {
                    resultRangeSet.add(Range.singleton(range.lowerEndpoint()));
                    if (newRange.lowerEndpoint() <= range.upperEndpoint()) {
                        resultRangeSet.add(Range.closed(newRange.lowerEndpoint(), Math.min(newRange.upperEndpoint(), range.upperEndpoint())));
                    }
                }
                if (range.upperEndpoint() > newRange.upperEndpoint()) {
                    resultRangeSet.add(Range.singleton(Math.max(newRange.upperEndpoint() + 1, range.lowerEndpoint())));
                }
            } else {
                // Non-intersecting ranges are added directly to the resultRangeSet
                resultRangeSet.add(range);
            }
        }
    }

    private static void splitAndAdd(RangeSet<Integer> resultRangeSet, Range<Integer> range) {
        // Check if the range is not a point
        if (!range.isEmpty() && range.lowerEndpoint() < range.upperEndpoint()) {
            // Add the first half of the range
            resultRangeSet.add(Range.closed(range.lowerEndpoint(), range.lowerEndpoint() + (range.upperEndpoint() - range.lowerEndpoint()) / 2));

            // Add the second half of the range
            resultRangeSet.add(Range.closed(range.lowerEndpoint() + (range.upperEndpoint() - range.lowerEndpoint()) / 2 + 1, range.upperEndpoint()));
        } else {
            // Add the single point range
            resultRangeSet.add(range);
        }
    }

}
