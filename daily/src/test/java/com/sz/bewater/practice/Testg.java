//package com.sz.bewater.practice;
//
//import com.google.common.collect.Range;
//import com.google.common.collect.RangeSet;
//import com.google.common.collect.TreeRangeSet;
//import com.sz.bewater.practice.common.IPSmartUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@SpringBootTest
//public class Testg {
//    @Test
//    public  void test() {
//        RangeSet rangeSet = TreeRangeSet.create();
//        rangeSet.add(Range.closed(IPSmartUtil.ipToNumber("173.11.13.20"), IPSmartUtil.ipToNumber("173.11.13.25")));
//        rangeSet.add(Range.closed(IPSmartUtil.ipToNumber("173.11.13.30"), IPSmartUtil.ipToNumber("173.11.13.35")));
//        Range<BigInteger> range = Range.closed(IPSmartUtil.ipToNumber("173.11.13.23"), IPSmartUtil.ipToNumber("173.11.13.32"));
//        RangeSet sub = rangeSet.subRangeSet(range);
//        if (sub.isEmpty()) {
//            System.out.println("没有交集直接新增");
//            return;
//        }
//        Range<BigInteger> span = sub.span();
//        System.out.println(IPSmartUtil.longToIP(span.lowerEndpoint()) + "-" + (IPSmartUtil.longToIP(span.upperEndpoint())));
//         if (rangeSet.encloses(range)){
//            System.out.println("包含");
//        }  else if (range.lowerEndpoint().compareTo(span.lowerEndpoint()) < 0 && range.upperEndpoint().compareTo(span.upperEndpoint()) > 0){
//            RangeSet large = TreeRangeSet.create();
//            large.add(Range.closed(range.lowerEndpoint(),span.lowerEndpoint().subtract(BigInteger.valueOf(1))));
//            large.add(Range.closed(span.upperEndpoint().add(BigInteger.valueOf(1)),range.upperEndpoint()));
//            Iterator<Range<BigInteger>> iterator = large.asRanges().iterator();
//            while (iterator.hasNext()){
//                Range<BigInteger> next = iterator.next();
//                System.out.println(IPSmartUtil.longToIP(next.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(next.upperEndpoint())));
//            }
//        } else if (range.lowerEndpoint().compareTo(span.lowerEndpoint()) < 0 && range.upperEndpoint().compareTo(span.lowerEndpoint()) >= 0) {
//            Range<BigInteger> left = Range.closed(range.lowerEndpoint(), span.lowerEndpoint().subtract(BigInteger.valueOf(1)));
//            System.out.println(IPSmartUtil.longToIP(left.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(left.upperEndpoint())));
//        } else if (span.upperEndpoint().compareTo(range.lowerEndpoint()) >= 0 && span.upperEndpoint().compareTo(range.upperEndpoint()) < 0) {
//            Range<BigInteger> right = Range.closed(span.upperEndpoint().add(BigInteger.valueOf(1)), range.upperEndpoint());
//            System.out.println(IPSmartUtil.longToIP(right.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(right.upperEndpoint())));
//        }
//        // 新的IP地址范围与rangeSet中的某个范围有交叉，获取交叉的部分
//
//
//            // 移除交叉的部分
//            // 移除newRange中与rangeSet交叉的部分
////            range = range.intersection(span);
//// 只保留newRange中与rangeSet无交集的部分
////            range = range.intersection(Range.closed(span.upperEndpoint(), range.upperEndpoint()));
////            System.out.println(IPSmartUtil.longToIP(range.lowerEndpoint().add(BigInteger.valueOf(1)))+"-"+(IPSmartUtil.longToIP(range.upperEndpoint())));
//
//
//
//    }
//
//    public static void handle(RangeSet<BigInteger> rangeSet,String targetStartIp,String targetEndIp){
////        RangeSet rangeSet = TreeRangeSet.create();
////        rangeSet.add(Range.closed(IPSmartUtil.ipToNumber("173.11.13.20"), IPSmartUtil.ipToNumber("173.11.13.25")));
//        Range<BigInteger> range = Range.closed(IPSmartUtil.ipToNumber(targetStartIp), IPSmartUtil.ipToNumber(targetEndIp));
//        RangeSet sub = rangeSet.subRangeSet(range);
//        if (sub.isEmpty()) {
//            System.out.println("没有交集直接新增");
//            rangeSet.add(Range.closed(IPSmartUtil.ipToNumber(targetEndIp), IPSmartUtil.ipToNumber(targetEndIp)));
//            return;
//        }
//        Range<BigInteger> span = sub.span();
//        System.out.println(IPSmartUtil.longToIP(span.lowerEndpoint()) + "-" + (IPSmartUtil.longToIP(span.upperEndpoint())));
//        if (rangeSet.encloses(range)){
//            System.out.println("包含");
//        }  else if (range.lowerEndpoint().compareTo(span.lowerEndpoint()) < 0 && range.upperEndpoint().compareTo(span.upperEndpoint()) > 0){
//            RangeSet large = TreeRangeSet.create();
//            large.add(Range.closed(range.lowerEndpoint(),span.lowerEndpoint().subtract(BigInteger.valueOf(1))));
//            large.add(Range.closed(span.upperEndpoint().add(BigInteger.valueOf(1)),range.upperEndpoint()));
//            Iterator<Range<BigInteger>> iterator = large.asRanges().iterator();
//            while (iterator.hasNext()){
//                Range<BigInteger> next = iterator.next();
//                System.out.println(IPSmartUtil.longToIP(next.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(next.upperEndpoint())));
//                rangeSet.add(Range.closed(next.lowerEndpoint(), next.upperEndpoint()));
//            }
//        } else if (range.lowerEndpoint().compareTo(span.lowerEndpoint()) < 0 && range.upperEndpoint().compareTo(span.lowerEndpoint()) >= 0) {
//            Range<BigInteger> left = Range.closed(range.lowerEndpoint(), span.lowerEndpoint().subtract(BigInteger.valueOf(1)));
//            System.out.println(IPSmartUtil.longToIP(left.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(left.upperEndpoint())));
//            rangeSet.add(Range.closed(left.lowerEndpoint(), left.upperEndpoint()));
//        } else if (span.upperEndpoint().compareTo(range.lowerEndpoint()) >= 0 && span.upperEndpoint().compareTo(range.upperEndpoint()) < 0) {
//            Range<BigInteger> right = Range.closed(span.upperEndpoint().add(BigInteger.valueOf(1)), range.upperEndpoint());
//            System.out.println(IPSmartUtil.longToIP(right.lowerEndpoint())+"-"+(IPSmartUtil.longToIP(right.upperEndpoint())));
//            rangeSet.add(Range.closed(right.lowerEndpoint(), right.upperEndpoint()));
//        }
//
//    }
//
//
//}
