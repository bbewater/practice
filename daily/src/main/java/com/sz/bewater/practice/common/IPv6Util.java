//package com.sz.bewater.practice.common;
//
//import cn.easysb.ip.jip.IPFormatType;
//import cn.easysb.ip.jip.JIPAddress;
//import cn.easysb.ip.jip.JIPAddressSet;
//import cn.easysb.ip.jip.JIPAddressUtils;
//import cn.easysb.ip.jip.advanced.JIPAddressCombiner;
//import cn.easysb.ip.jip.advanced.JIPAddressIntersecter;
//import cn.easysb.ip.jip.advanced.JIPAddressSubtracter;
//import cn.easysb.ip.jip.advanced.JIPAddressUnioner;
//import com.sz.bewater.practice.common.ipv6.*;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//
//import java.math.BigInteger;
//import java.util.*;
//
///**
// * 对操作的目标IP进行合法性校验，以及对目标IP进行在用户所属的IP范围内校验
// */
//public class IPv6Util {
//
//
//    public final static String ipV6Style1 = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|" +
//            "(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|" +
//            "([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|" +
//            "[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|" +
//            "25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))(\\/([1-9]|[1-9][0-9]|[1][0-2][0-8]))$";
//
//    public final static String ipV6Style2 = "^(((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|" +
//            "(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|" +
//            "(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|" +
//            "([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|" +
//            "[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|" +
//            "2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|" +
//            "(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|" +
//            "25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))\\-?){1,2}$";
//
//
//    /**
//     * 校验IP格式是否正确,格式正确返回true，否则为false
//     * 支持格式示例：
//     *                 2000:0000:0000:0000:0001:2345:6789:abcd
//     *                 2000:0:0:0:1:2345:6789:abcd
//     *                 2000::1:2345:6789:abcd
//     *                 2000::1:2345:6789:abcd-2000::1:2345:6789:abcf
//     *                 2000::1:2345:6789:abcd/127
//     *
//     * @return 格式正确返回true，否则为false
//     */
//    public static boolean validateIPFormat(String ip) {
//        if (ip.matches(ipV6Style1) || ip.matches(ipV6Style2)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isIpV6(String ip) {
//        return ip.indexOf(":") != -1;
//    }
//
//    /**
//     * 校验两个IP地址段是否一致
//     *
//     * @param sourceIPRange IP地址段，多段之间用逗号分隔，每段接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段的四种格式
//     * @param targetIPRange IP地址段，多段之间用逗号分隔，每段接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段的四种格式
//     * @return 一致则返回true，否则为false
//     */
//    public static boolean validateIPEqual(String sourceIPRange, String targetIPRange) {
//        if (sourceIPRange.contains(",") || targetIPRange.contains(",")) {
//            /* IP地址段字符串中有多个用逗号分隔的IP段，则采用以下比较方法：
//             * targetIPRange是sourceIPRange的子集，同时sourceIPRange也是targetIPRange的子集，则两者完全相等
//             */
//            String[] sourceIPRanges = sourceIPRange.split(",");
//            String[] targetIPRanges = targetIPRange.split(",");
//            return checkIPRangeIncluded(sourceIPRanges, targetIPRanges)
//                    && checkIPRangeIncluded(targetIPRanges, sourceIPRanges);
//        } else {
//            //进行比较的都是单一的IP地址段，直接比较两者的起始和截止地址
////            return getBeginAddress(sourceIPRange) == getBeginAddress(targetIPRange)
////                    && getEndAddress(sourceIPRange) == getEndAddress(targetIPRange);
//            return getBeginAddress(sourceIPRange).compareTo(getBeginAddress(targetIPRange))==0
//                    &&  getEndAddress(sourceIPRange).compareTo(getEndAddress(targetIPRange))==0;
//
//        }
//    }
//
//    public static boolean validateIPv6Equal(String sourceIPRange, String targetIPRange) {
//        if (sourceIPRange.contains(",") || targetIPRange.contains(",")) {
//            /* IP地址段字符串中有多个用逗号分隔的IP段，则采用以下比较方法：
//             * targetIPRange是sourceIPRange的子集，同时sourceIPRange也是targetIPRange的子集，则两者完全相等
//             */
//            String[] sourceIPRanges = sourceIPRange.split(",");
//            String[] targetIPRanges = targetIPRange.split(",");
//            List<String> sourceIPList = Arrays.asList(sourceIPRanges);
//            List<String> targetIPList = Arrays.asList(targetIPRanges);
//            return checkIPv6RangeIncluded(sourceIPList, targetIPList)
//                    && checkIPv6RangeIncluded(targetIPList, sourceIPList);
//        } else {
//            //进行比较的都是单一的IP地址段，直接比较两者的起始和截止地址
////            return getBeginAddress(sourceIPRange) == getBeginAddress(targetIPRange)
////                    && getEndAddress(sourceIPRange) == getEndAddress(targetIPRange);
//
//            return getBeginAddress(sourceIPRange).compareTo(getBeginAddress(targetIPRange))==0
//                    &&  getEndAddress(sourceIPRange).compareTo(getEndAddress(targetIPRange))==0;
//        }
//    }
//
//    /**
//     * 校验一组IP段之间是否有交集
//     *
//     * @param ipRanges IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return true：有交集，false：无交集
//     */
//    public static boolean checkIPRangeIntersected(String... ipRanges) {
//        //求出IP地址段的起始和截止IP
//        double[][] longIPRanges = ipRangesToLongRanges(ipRanges).toArray(new double[ipRanges.length][]);
//        //比较是否有交集
//        for (int i = 0; i < ipRanges.length; i++) {
//            for (int j = i + 1; j < ipRanges.length; j++) {
//                if (longIPRanges[i][0] <= longIPRanges[j][1]
//                        && longIPRanges[i][1] >= longIPRanges[j][0]) {
//                    //A的起始地址小于等于B的截止地址并且A的截止地址大于等于B的起始地址，则有交集
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * 校验一组IP段之间是否有交集
//     *
//     * @param ipRanges IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return true：有交集，false：无交集
//     */
//    public static boolean checkIPRangeIntersected(Collection<String> ipRanges) {
//        return checkIPRangeIntersected(ipRanges.toArray(new String[ipRanges.size()]));
//    }
//
//    public static boolean checkIPv6RangeIntersected(List<String> ipRanges) {
//        //比较是否有交集
//        for (int i = 0; i < ipRanges.size(); i++) {
//            for (int j = i+1; j < ipRanges.size(); j++) {
//                JIPAddress jipAddress1 = JIPAddressUtils.toIpObject(ipRanges.get(i));
//                JIPAddress jipAddress2 = JIPAddressUtils.toIpObject(ipRanges.get(j));
//                List<JIPAddress> addressList = JIPAddressIntersecter.intersect(jipAddress1, jipAddress2);
//                if (CollectionUtils.isNotEmpty(addressList)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 判断一组IP段是否完全包含了另一IP段
//     *
//     * @param sourceRanges 全集IP地址段
//     * @param toCheckRange 待校验IP地址段
//     * @return 当toCheckRange为sourceRanges的子集时为true，否则为false
//     */
//    public static boolean checkIPRangeIncluded(Collection<String> sourceRanges, String toCheckRange) {
//        String[] empty = new String[0];
//        return checkIPRangeIncluded(sourceRanges.toArray(empty), toCheckRange);
//    }
//
//    /**
//     * 判断一组IP段是否完全包含了另一IP段
//     *
//     * @param sourceRanges 全集IP地址段
//     * @param toCheckRange 待校验IP地址段
//     * @return 当toCheckRange为sourceRanges的子集时为true，否则为false
//     */
//    public static boolean checkIPRangeIncluded(String[] sourceRanges, String toCheckRange) {
//        return checkIPRangeIncluded(sourceRanges, new String[]{toCheckRange});
//    }
//
//    /**
//     * 判断一组IP段是否完全包含了另一IP段
//     *
//     * @param sourceRanges  全集IP地址段
//     * @param toCheckRanges 待校验IP地址段
//     * @return 当toCheckRange为sourceRanges的子集时为true，否则为false
//     */
//    public static boolean checkIPRangeIncluded(Collection<String> sourceRanges, Collection<String> toCheckRanges) {
//        String[] empty = new String[0];
//        return checkIPRangeIncluded(sourceRanges.toArray(empty), toCheckRanges.toArray(empty));
//    }
//
//    /**
//     * 判断一组IP段是否完全包含了另一IP段
//     *
//     * @param sourceRanges  全集IP地址段
//     * @param toCheckRanges 待校验IP地址段
//     * @return 当toCheckRange为sourceRanges的子集时为true，否则为false
//     */
//    public static boolean checkIPv6RangeIncluded(List<String> sourceRanges, List<String> toCheckRanges) {
//        JIPAddressSet sourceAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        JIPAddressSet toCheckAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        sourceAddressSet = JIPAddressUtils.addIpList(sourceAddressSet, sourceRanges, null);
//        toCheckAddressSet = JIPAddressUtils.addIpList(toCheckAddressSet, toCheckRanges, null);
//        List<JIPAddress> unionAddressList = JIPAddressUnioner.union(sourceAddressSet.all(), toCheckAddressSet.all());
//        List<JIPAddress> addressList = JIPAddressSubtracter.subtract(unionAddressList, sourceAddressSet.all());
//        if(CollectionUtils.isEmpty(addressList)){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    /**
//     * 判断一组IP段是否完全包含了另一IP段
//     *
//     * @param sourceRanges  源IP地址段，IP地址段接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param toCheckRanges 待校验IP地址段，IP地址段接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 当toCheckRanges为sourceRanges的子集时为true，否则为false
//     */
//    public static boolean checkIPRangeIncluded(String[] sourceRanges, String[] toCheckRanges) {
//
//        for (String toCheckRange : toCheckRanges) {
//            IPv6AddressRange targetNetwork = null;
//            if (toCheckRange.contains("-")) {
//                targetNetwork = IPv6AddressRange.fromFirstAndLast(IPv6Address.fromString(toCheckRange.split("-")[0]), IPv6Address.fromString(toCheckRange.split("-")[1]));
//            } else {
//                targetNetwork = IPv6Network.fromString(toCheckRange);
//            }
//            boolean contains = false;
//            for (String sourceRange : sourceRanges) {
//                IPv6AddressRange sourceNetwork = null;
//                if (sourceRange.contains("-")) {
//                    sourceNetwork = IPv6AddressRange.fromFirstAndLast(IPv6Address.fromString(sourceRange.split("-")[0]), IPv6Address.fromString(sourceRange.split("-")[1]));
//                } else {
//                    sourceNetwork = IPv6Network.fromString(sourceRange);
//                }
//                contains = sourceNetwork.contains(targetNetwork);
//                if (contains) {
//                    break;
//                }
//            }
//            if (!contains) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 取两个IP段之间的交集
//     *
//     * @param ranges1 第一个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param ranges2 第二个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 交集IP地址段的字符串表示
//     */
//    public static List<String> getIPRangeIntersection(Collection<String> ranges1, Collection<String> ranges2) {
//        return getIPRangeIntersection(
//                ranges1.toArray(new String[ranges1.size()]),
//                ranges2.toArray(new String[ranges2.size()])
//        );
//    }
//
//    /**
//     * 取两个IP段之间的交集
//     *
//     * @param sourceRanges  第一个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param toCheckRanges 第二个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 交集IP地址段的字符串表示
//     */
//    public static List<String> getIPRangeIntersection(String[] sourceRanges, String[] toCheckRanges) {
//        List<String> source = getIpList(sourceRanges);
//        List<String> target = getIpList(toCheckRanges);
//        return new ArrayList<>(CollectionUtils.intersection(source, target));
//    }
//
//    public static List<String> getIPv6RangeIntersection(List<String> sourceRanges, List<String> toCheckRanges) {
//        List<String> intersectionList = new ArrayList<>();
//        JIPAddressSet sourceAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        JIPAddressSet toCheckAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        sourceAddressSet = JIPAddressUtils.addIpList(sourceAddressSet, sourceRanges, null);
//        toCheckAddressSet = JIPAddressUtils.addIpList(toCheckAddressSet, toCheckRanges, null);
//        List<JIPAddress> addressList = JIPAddressIntersecter.intersect(toCheckAddressSet.all(), sourceAddressSet.all());
//        if(!CollectionUtils.isEmpty(addressList)){
//            intersectionList = JIPAddressUtils.toIpStringList(addressList, IPFormatType.SEGMENT_FULL_FIRST);
//        }
//        return intersectionList;
//    }
//
//    public static List<String> getIPv6RangeDisjoint(List<String> sourceRangesIpV6List, List<String> toCheckRangesIpV6List) {
//        List<String> disjointList = new ArrayList<>();
//        JIPAddressSet sourceAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        JIPAddressSet toCheckAddressSet = JIPAddressUtils.buildEmptyAddressSet();
//        sourceAddressSet = JIPAddressUtils.addIpList(sourceAddressSet, sourceRangesIpV6List, null);
//        toCheckAddressSet = JIPAddressUtils.addIpList(toCheckAddressSet, toCheckRangesIpV6List, null);
//        List<JIPAddress> subtractAddressList = JIPAddressSubtracter.subtract(toCheckAddressSet.all(), sourceAddressSet.all());
//        if(CollectionUtils.isNotEmpty(subtractAddressList)){
//            //取差集后将ip地址段展开成ip散列
//            List<JIPAddress> iplist = new ArrayList<>();
//            for(JIPAddress ipAddress : subtractAddressList){
//                List<JIPAddress> addressIPList = JIPAddressUtils.expandIpList(JIPAddressUtils.toIpObject(ipAddress.toString()), 0);
//                iplist.addAll(addressIPList);
//            }
//            disjointList = JIPAddressUtils.toIpStringList(iplist, IPFormatType.SEGMENT_SIMPLE_FIRST);
//        }
//        return disjointList;
//    }
//
//
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 拼接后的有序连续的IP地址段
//     */
//    public static List<String> concatIPRanges(List<String> ipRanges) {
//        return concatIPRanges(ipRanges.toArray(new String[ipRanges.size()]));
//    }
//
//    public static List<String> concatIPv6Ranges(List<String> ipRanges) {
//        JIPAddressSet addressSet = JIPAddressUtils.buildEmptyAddressSet();
//        addressSet = JIPAddressUtils.addIpList(addressSet, ipRanges, null);
//        List<JIPAddress> addressList = JIPAddressCombiner.combine(addressSet.all());
//        return JIPAddressUtils.toIpStringList(addressList, IPFormatType.SEGMENT_SIMPLE_FIRST);
//    }
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 拼接后的有序连续的IP地址段
//     */
//    public static List<String> concatIPRanges(String... ipRanges) {
//        return getIpList(ipRanges);
//    }
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 非重复IP记数
//     */
//    public static long getRangeIPCount(String... ipRanges) {
//        long ipCount = 0L;
//        for (String ipRange : ipRanges) {
//            if (ipRange.contains("-")) {
//                ipCount += IPv6AddressRange.fromFirstAndLast(IPv6Address.fromString(ipRange.split("-")[0]), IPv6Address.fromString(ipRange.split("-")[1])).size().longValue();
//                continue;
//            }
//            ipCount += IPv6Network.fromString(ipRange).size().longValue();
//        }
//        return ipCount;
//    }
//
//    public static long getRangeIPv6Count(List<String> ipRanges) {
//        JIPAddressSet addressSet = JIPAddressUtils.buildEmptyAddressSet();
//        addressSet = JIPAddressUtils.addIpList(addressSet, ipRanges, null);
//        List<JIPAddress> addressList = JIPAddressCombiner.combine(addressSet.all());
//        List<JIPAddress> iPList = JIPAddressUtils.expandIpList(addressList, 0);
//        if(CollectionUtils.isNotEmpty(iPList)){
//            return iPList.size();
//        }
//        return 0L;
//    }
//
//    /**
//     * 将一组IP地址段拼接，并按起始IP从小到大排序，再转换成点分十进制IPv4/掩码位数或IPv4 点分十进制掩码的表示方式
//     *
//     * @param showMaskSize 为true时返回的格式为：点分十进制IPv4/掩码位数，否则为IPv4 点分十进制掩码
//     * @param ipRanges     待转换的IP地址范围
//     * @return 转换后的IP地址段
//     */
//    public static List<String> changeIPRangesToSubNets(boolean showMaskSize, String... ipRanges) {
//        return changeIPRangesToSubNets(1, showMaskSize, ipRanges);
//    }
//
//    /**
//     * 将一组IP地址段拼接，并按起始IP从小到大排序，再转换成点分十进制IPv4/掩码位数或IPv4 点分十进制掩码的表示方式
//     *
//     * @param minMaskSize  允许的最小的掩码位数
//     * @param showMaskSize 为true时返回的格式为：点分十进制IPv4/掩码位数，否则为IPv4 点分十进制掩码
//     * @param ipRanges     待转换的IP地址范围
//     * @return 转换后的IP地址段
//     */
//    public static List<String> changeIPRangesToSubNets(int minMaskSize, boolean showMaskSize, String... ipRanges) {
//        //先将字符串型IP地址段转换成数值型起止IP数组
//        List<String> resultList = new ArrayList<>();
//        for (String ipRange : ipRanges) {
//            Iterator<IPv6Network> networks = null;
//            if (ipRange.contains("-")) {
//                ipRange = IPSmartUtil.convertFormat(ipRange);
//                networks = IPv6AddressRange.fromFirstAndLast(IPv6Address.fromString(ipRange.split("-")[0]), IPv6Address.fromString(ipRange.split("-")[1])).toSubnets();
//            } else {
//                networks = IPv6Network.fromString(ipRange).toSubnets();
//            }
//            while (networks.hasNext()) {
//                resultList.add(networks.next().toString());
//            }
//        }
//        return resultList;
//    }
//
//
//    /**
//     * 校验IP地址范围是否有交集（保留旧有的方法名）
//     *
//     * @param sourceIP IP范围，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param targetIP 待校验的IP范围，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 有交集返回true，否则返回false
//     */
//    public static boolean validateIPRange(String sourceIP, String targetIP) {
//        return checkIPRangeIntersected(sourceIP, targetIP);
//    }
//
//
//    /**
//     * 计算网络地址
//     *
//     * @param ip 接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 三种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 网络地址（即子网起始IP）
//     */
//    public static String getNetworkAddress(String ip) {
//        //接受IP、IP 掩码、IP/掩码位数三种格式
//        return numberToIP(getBeginAddress(ip));
//    }
//
//    /**
//     * 计算网络地址
//     *
//     * @param ip   点分十进制表示的IPv4地址
//     * @param mask 点分十进制表示的子网掩码或十进制数值表示的子网掩码位数
//     * @return 网络地址（即子网起始IP）
//     */
//    public static String getNetworkAddress(String ip, String mask) {
//        return numberToIP(getBeginAddress(ip + "/" + mask));
//    }
//
//    /**
//     * 计算第一个ip地址，并将其转换为long型数字
//     *
//     * @param ipRange IP地址段，接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 或者 IPv4-IPv4片段 四种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 起始IP的long型数值
//     */
//    public static BigInteger getBeginAddress(String ipRange) {
//
//        String[] rangeParts = null;
//        if (ipRange.contains("-")) {
//            // '-'隔开的地址段
//            rangeParts = ipRange.split("-");
//            BigInteger begin = IPv6Address.fromString(rangeParts[0]).toBigInteger();
//            BigInteger end = IPv6Address.fromString(rangeParts[1]).toBigInteger();
//            return end.compareTo(begin) > 0 ? begin : end;
//        }
//        return IPv6Network.fromString(ipRange).getFirst().toBigInteger();
//    }
//
//    /**
//     * 计算最后一位IP地址，将其转换为long型数字
//     *
//     * @param ipRange IP地址段，接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 或者 IPv4-IPv4片段 四种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 最后一位IP的long型数值
//     */
//    public static BigInteger getEndAddress(String ipRange) {
//        String[] rangeParts = null;
//        if (ipRange.contains("-")) {
//            rangeParts = ipRange.split("-");
//            BigInteger begin = IPv6Address.fromString(rangeParts[0]).toBigInteger();
//            BigInteger end = IPv6Address.fromString(rangeParts[1]).toBigInteger();
//            return end.compareTo(begin) > 0 ? end : begin;
//        }
//        return IPv6Network.fromString(ipRange).getLast().toBigInteger();
//    }
//
//    /**
//     * 根据IP地址(点分十进制)、子网掩码获得子网起始地址
//     *
//     * @param num       掩码位数
//     * @param ipAddress IP地址(点分十进制)
//     * @return String 点分十进制子网起始地址
//     */
//    public static String getBeginSubnetAddress(int num, String ipAddress) {
//        return IPv6Network.fromAddressAndMask(IPv6Address.fromString(ipAddress), IPv6NetworkMask.fromPrefixLength(num)).getFirst().toLongString();
//    }
//
//
//    /**
//     * 根据子网地址(十进制)、子网掩码位数计算网段最后的IP地址
//     *
//     * @param num 掩码位数
//     * @param ip  点分十进制IP
//     * @return 点分十进制子网最后一位IP
//     */
//    public static String getEndSubnetAddress(int num, String ip) {
//        return IPv6Network.fromAddressAndMask(IPv6Address.fromString(ip), IPv6NetworkMask.fromPrefixLength(num)).getLast().toLongString();
//    }
//
//    /**
//     * 将16进制整数形式转换成ipv6形式的IP地址
//     *
//     * @param ip IP地址的数值表示
//     * @return 点分十进制表示的IP地址
//     */
//    public static String numberToIP(BigInteger ip) {
//        return IPv6AddressHelpers.numberToIp(ip);
//    }
//
//    /**
//     * 将127.0.0.1 形式的IP地址转换成10进制整数
//     *
//     * @param strIP 点分十进制表示的IP地址
//     * @return IP地址的数值表示
//     */
//    public static BigInteger ipToNumber(String strIP) {
//        return IPv6AddressHelpers.ipToNumber(strIP);
//    }
//
//
//    /**
//     * 取出IP地址段中的所有IP
//     *
//     * @param ipRange IP地址段
//     * @return IP列表
//     */
//    public static List<String> getIpList(String ipRange) {
//        Iterable<IPv6Address> addresses = null;
//
//        if (ipRange.contains("-")) {
//            addresses = IPv6AddressRange.fromFirstAndLast(IPv6Address.fromString(ipRange.split("-")[0]), IPv6Address.fromString(ipRange.split("-")[1]));
//        } else {
//            addresses = IPv6Network.fromString(ipRange);
//        }
//        Set<String> ipList = new LinkedHashSet<>();
//        for (IPv6Address ip : addresses) {
//            ipList.add(ip.toLongString());
//        }
//        return new ArrayList<>(ipList);
//    }
//
//    public static List<String> getIpList(String... ipRange) {
//        Set<String> ipList = new LinkedHashSet<>();
//        for (String ip : ipRange) {
//            ipList.addAll(getIpList(ip));
//        }
//        return new ArrayList<>(ipList);
//    }
//
//    public static List<String> getIpList(Collection<String> ipRange) {
//        Set<String> ipList = new LinkedHashSet<>();
//        for (String ip : ipRange) {
//            ipList.addAll(getIpList(ip));
//        }
//        return new ArrayList<>(ipList);
//    }
//
//    /**
//     * 根据Ip地址和子网掩码位数  查询所有该网段的地址
//     *
//     * @param ip      ip地址（通常是网络地址）
//     * @param maskNum 子网掩码位数 （如 24,28,30）
//     * @return 网络ip集合
//     */
//    public static List<String> getIpListByIpMask(String ip, Integer maskNum) {
//        return getIpList(ip + "/" + maskNum);
//    }
//
//
//    /**
//     * 根据掩码返回掩码位数
//     *
//     * @param mask 点分十进制表示的子网掩码或者字符串表示的掩码位数
//     * @return 子网掩码位数
//     */
//    private static int getSubnetMaskSizeFromMask(String mask) {
//        return IPv6NetworkMask.fromPrefixLength(Integer.valueOf(mask)).asPrefixLength();
//    }
//
//
//    /**
//     * 将一组IP地址段转换成数值型起止IP数组表示的IP地址段
//     *
//     * @param ipRanges IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段的四种格式
//     * @return 以数值型起止IP数组表示的IP地址段
//     */
//    private static List<double[]> ipRangesToLongRanges(String... ipRanges) {
//        List<double[]> longList = new ArrayList<>(ipRanges.length);
//        for (String range : ipRanges) {
//            longList.add(
//                    new double[]{
//                            getBeginAddress(range).doubleValue(),
//                            getEndAddress(range).doubleValue()
//                    }
//            );
//        }
//        return longList;
//    }
//
//	/**
//	 * ipv6地址转换为全编码格式 1000::FFFF:FFFF -> 1000:0000:0000:0000:0000:0000:ffff:ffff
//	 *
//	 * @param ip
//	 * @return 全小写
//	 */
//	public static String IPV6Format(String ip) {
//		if (ObjectUtils.isEmpty(ip)) {
//			return "";
//		}
//
//		ip = ip.trim();
//
//		if (ip.equals("::")) {
//			return "0000:0000:0000:0000:0000:0000:0000:0000";
//		}
//
//		String returnIP = "";
//		String tmpstr = ip;
//		String convertstr = "";
//		try {
//			//如果是v4格式，则首先变为v6格式
//
//			if (tmpstr.indexOf(".") > 0) {
//				tmpstr = tmpstr.substring(tmpstr.lastIndexOf(":") + 1, tmpstr.length());
//
//				tmpstr = tmpstr + ".";
//
//				for (int i = 0; i < 4; i++) {
//					String substr = tmpstr.substring(0, tmpstr.indexOf("."));
//
//					if (i != 3) {
//						tmpstr = tmpstr.substring(tmpstr.indexOf(".") + 1, tmpstr.length());
//					}
//
//					int subint = Integer.parseInt(substr);
//
//					if (i == 2) {
//						convertstr = convertstr + ":";
//					}
//
//					substr = Integer.toHexString(subint);
//
//					if (substr.length() == 1) {
//						substr = "0" + substr;
//					}
//
//					convertstr = convertstr + substr;
//				}
//
//				ip = ip.substring(0, ip.lastIndexOf(":") + 1);
//				ip = ip + convertstr;
//			}
//
//			// 判断：数量
//			tmpstr = ip;
//			int marknum = 0;
//
//			int index;
//
//			while ((index = tmpstr.indexOf(":")) != -1) {
//				marknum++;
//
//				tmpstr = tmpstr.substring(index + 1);
//
//				if (tmpstr == null) {
//					break;
//				}
//			}
//
//			index = ip.indexOf("::");
//
//			if (index != -1) {
//				marknum--;
//
//				convertstr = ":";
//
//				if (index == 0 || index == ip.length() - 2) {
//					marknum--;
//				}
//
//				while (marknum < 7) {
//					convertstr += "0000:";
//					marknum++;
//				}
//
//				if (convertstr.equals(":"))
//					return "error";
//
//				if (index == 0) {
//					convertstr = convertstr.substring(1);
//				} else if (index == ip.length() - 2) {
//					convertstr = convertstr.substring(0, convertstr.length() - 1);
//				}
//
//				ip = ip.replaceFirst("::", convertstr);
//
//				if (ip.indexOf("::") != -1) {
//					return "error";
//				}
//			} else if (marknum != 7)
//				return "error";
//
//			// 进行地址格式化
//			ip += ":";
//
//			for (int i = 0; i < 8; i++) {
//				String substr = ip.substring(0, ip.indexOf(":"));
//
//				if (substr == null || substr.trim().equals(""))
//					return "error";
//
//				if (i != 7) {
//					ip = ip.substring(ip.indexOf(":") + 1, ip.length());
//				}
//
//				if (substr.length() > 4) {
//					return "error";
//				} else if (substr.length() == 3) {
//					substr = "0" + substr;
//				} else if (substr.length() == 2) {
//					substr = "00" + substr;
//				} else if (substr.length() == 1) {
//					substr = "000" + substr;
//				}
//
//				returnIP = returnIP + ":" + substr;
//			}
//
//			returnIP = returnIP.substring(1);
//
//			returnIP = returnIP.toLowerCase();
//
//			return returnIP;
//		} catch (Exception e) {
//			return "error";
//		}
//	}
//}
