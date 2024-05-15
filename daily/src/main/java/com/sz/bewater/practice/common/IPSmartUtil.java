//package com.sz.bewater.practice.common;
//
//import cn.easysb.ip.jip.IPFormatType;
//import cn.easysb.ip.jip.IPType;
//import cn.easysb.ip.jip.JIPAddress;
//import cn.easysb.ip.jip.JIPAddressUtils;
//import cn.easysb.ip.jip.advanced.JIPAddressIntersecter;
//import org.apache.commons.collections.CollectionUtils;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.regex.Pattern;
//
///**
// * 对操作的目标IP进行合法性校验，以及对目标IP进行在用户所属的IP范围内校验
// */
//public class IPSmartUtil {
//
//    public static final BigInteger IPV4_MAX = new BigInteger("4294967295");
//
//    private static final Pattern ipv4pattern = Pattern.compile(
//            "(25[0-5]|2[0-4]\\d|1\\d{0,2}|0\\d{0,2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{0,2}|0\\d{0,2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{0,2}|0\\d{0,2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{0,2}|0\\d{0,2}|\\d{1,2})");
//
//    private static final Pattern ipv6pattern = Pattern.compile(
//            "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:"
//                    + "[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:)"
//                    + "{3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|"
//                    + "(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]))"
//                    + "{3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]"
//                    + "{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:)"
//                    + "{3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}"
//                    + "(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4})"
//                    + "{0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])"
//                    + "(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");
//    /**
//     * 支持IPV4、IPV6
//     * 校验IP格式是否正确,格式正确返回true，否则为false
//     *
//     * @return 格式正确返回true，否则为false
//     */
//    public static boolean validateIPFormat(String ip) {
//        ip = convertFormat(ip);
//        if (ipv4pattern.matcher(ip).matches() || ipv6pattern.matcher(ip).matches()) {
//            return  true;
//        }
//        return false;
//    }
//
//    /**
//     * v6地址段，
//     * 240e:fb:afff:f008::1-6345需要转换成240e:fb:afff:f008::1-240e:fb:afff:f008::6345
//     * @param ip
//     * @return
//     */
//    public static String convertFormat(String ip){
//        if(!isIpV6(ip)){
//            return ip;
//        }
//        if(!ip.contains("-")
//                || ip.contains("/")){
//            return ip;
//        }
//        if(ip.endsWith("-")){
//            return ip;
//        }
//        //240e:fb:afff:f008::1-6345
//        String[] parts = ip.split("-");
//        if(parts[1].contains(":")){
//            return ip;
//        }
//        String prefix = parts[0].substring(0,parts[0].lastIndexOf(":")+1);
//        return parts[0] + "-" + prefix+parts[1];
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
//        if (isIpV6(sourceIPRange)) {
//            //return IPv6Util.validateIPEqual(sourceIPRange, targetIPRange);
//            return IPv6Util.validateIPv6Equal(sourceIPRange, targetIPRange);
//        }
//        return IPUtil.validateIPEqual(sourceIPRange, targetIPRange);
//    }
//
//    /**
//     * 校验一组IP段之间是否有交集
//     *
//     * @param ipRanges IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return true：有交集，false：无交集
//     */
//    public static boolean checkIPRangeIntersected(String... ipRanges) {
//        List<String> ipV4List = getIpV4List(ipRanges);
//        List<String> ipV6List = getIpV6List(ipRanges);
//        //return IPUtil.checkIPRangeIntersected(ipV4List) || IPv6Util.checkIPRangeIntersected(ipV6List);
//        return IPUtil.checkIPRangeIntersected(ipV4List) || IPv6Util.checkIPv6RangeIntersected(ipV6List);
//    }
//
//    /**
//     * 校验一组IP段之间是否有交集
//     *
//     * @param ipRanges IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return true：有交集，false：无交集
//     */
//    public static boolean checkIPRangeIntersected(Collection<String> ipRanges) {
//        List<String> ipV4List = getIpV4List(ipRanges);
//        List<String> ipV6List = getIpV6List(ipRanges);
//        //return IPUtil.checkIPRangeIntersected(ipV4List) || IPv6Util.checkIPRangeIntersected(ipV6List);
//        return IPUtil.checkIPRangeIntersected(ipV4List) || IPv6Util.checkIPv6RangeIntersected(ipV6List);
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
//        List<String> sourceIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckIpV4List = getIpV4List(toCheckRange);
//        List<String> toCheckIpV6List = getIpV6List(toCheckRange);
//        //return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPRangeIncluded(sourceIpV6List, toCheckIpV6List);
//        return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPv6RangeIncluded(sourceIpV6List, toCheckIpV6List);
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
//        List<String> sourceIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckIpV4List = getIpV4List(toCheckRange);
//        List<String> toCheckIpV6List = getIpV6List(toCheckRange);
//        //return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPRangeIncluded(sourceIpV6List, toCheckIpV6List);
//        return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPv6RangeIncluded(sourceIpV6List, toCheckIpV6List);
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
//        List<String> sourceIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckIpV4List = getIpV4List(toCheckRanges);
//        List<String> toCheckIpV6List = getIpV6List(toCheckRanges);
//        //return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPRangeIncluded(sourceIpV6List, toCheckIpV6List);
//        return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPv6RangeIncluded(sourceIpV6List, toCheckIpV6List);
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
//        List<String> sourceIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckIpV4List = getIpV4List(toCheckRanges);
//        List<String> toCheckIpV6List = getIpV6List(toCheckRanges);
//        //return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPRangeIncluded(sourceIpV6List, toCheckIpV6List);
//        return IPUtil.checkIPRangeIncluded(sourceIpV4List, toCheckIpV4List) && IPv6Util.checkIPv6RangeIncluded(sourceIpV6List, toCheckIpV6List);
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
//        List<String> ranges1IpV4List = getIpV4List(ranges1);
//        List<String> ranges1IpV6List = getIpV6List(ranges1);
//        List<String> ranges2IpV4List = getIpV4List(ranges2);
//        List<String> ranges2IpV6List = getIpV6List(ranges2);
//        List<String> responList = new ArrayList<>();
//        responList.addAll(IPUtil.getIPRangeIntersection(ranges1IpV4List, ranges2IpV4List));
//        //responList.addAll(IPv6Util.getIPRangeIntersection(ranges1IpV6List, ranges2IpV6List));
//        if(!CollectionUtils.isEmpty(ranges1IpV6List)){
//            responList.addAll(IPv6Util.getIPv6RangeIntersection(ranges1IpV6List, ranges2IpV6List));
//        }
//        return responList;
//    }
//
//    /**
//     * 取两个IP段之间的交集
//     *
//     * @param ranges1 第一个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param ranges2 第二个IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 交集IP地址段的字符串表示
//     */
//    public static List<String> getIPRangeIntersection(String[] ranges1, String[] ranges2) {
//        List<String> ranges1IpV4List = getIpV4List(ranges1);
//        List<String> ranges1IpV6List = getIpV6List(ranges1);
//        List<String> ranges2IpV4List = getIpV4List(ranges2);
//        List<String> ranges2IpV6List = getIpV6List(ranges2);
//        List<String> responList = new ArrayList<>();
//        responList.addAll(IPUtil.getIPRangeIntersection(ranges1IpV4List, ranges2IpV4List));
//        //responList.addAll(IPv6Util.getIPRangeIntersection(ranges1IpV6List, ranges2IpV6List));
//        if(!CollectionUtils.isEmpty(ranges1IpV6List)){
//            responList.addAll(IPv6Util.getIPv6RangeIntersection(ranges1IpV6List, ranges2IpV6List));
//        }
//        return responList;
//    }
//
//    /**
//     * 求出要校验的IP地址段中不在源IP地址段的部分
//     *
//     * @param sourceRanges  源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param toCheckRanges 要校验的IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 要校验的IP地址段不在源IP地址段中的部分
//     */
//    public static List<String> getIPRangeDisjoint(Collection<String> sourceRanges, Collection<String> toCheckRanges) {
//        List<String> sourceRangesIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceRangesIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckRangesIpV4List = getIpV4List(toCheckRanges);
//        List<String> toCheckRangesIpV6List = getIpV6List(toCheckRanges);
//        List<String> responList = new ArrayList<>();
//        //responList.addAll(IPUtil.getIPRangeDisjoint(sourceRangesIpV4List, toCheckRangesIpV4List));
//        responList.addAll(IPUtil.getIPv4RangeDisjoint(sourceRangesIpV4List, toCheckRangesIpV4List));
//        //responList.addAll(IPv6Util.getIPRangeDisjoint(sourceRangesIpV6List, toCheckRangesIpV6List));
//        responList.addAll(IPv6Util.getIPv6RangeDisjoint(sourceRangesIpV6List, toCheckRangesIpV6List));
//        return responList;
//    }
//
//    /**
//     * 求出要校验的IP地址段中不在源IP地址段的部分
//     *
//     * @param sourceRanges  源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param toCheckRanges 要校验的IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 要校验的IP地址段不在源IP地址段中的部分
//     */
//    public static List<String> getIPRangeDisjoint(String[] sourceRanges, String[] toCheckRanges) {
//        List<String> sourceRangesIpV4List = getIpV4List(sourceRanges);
//        List<String> sourceRangesIpV6List = getIpV6List(sourceRanges);
//        List<String> toCheckRangesIpV4List = getIpV4List(toCheckRanges);
//        List<String> toCheckRangesIpV6List = getIpV6List(toCheckRanges);
//        List<String> responList = new ArrayList<>();
//        //responList.addAll(IPUtil.getIPRangeDisjoint(sourceRangesIpV4List, toCheckRangesIpV4List));
//        responList.addAll(IPUtil.getIPv4RangeDisjoint(sourceRangesIpV4List, toCheckRangesIpV4List));
//        //responList.addAll(IPv6Util.getIPRangeDisjoint(sourceRangesIpV6List, toCheckRangesIpV6List));
//        responList.addAll(IPv6Util.getIPv6RangeDisjoint(sourceRangesIpV6List, toCheckRangesIpV6List));
//        return responList;
//    }
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 拼接后的有序连续的IP地址段
//     */
//    public static List<String> concatIPRanges(List<String> ipRanges) {
//        List<String> ipRangesIpV4List = getIpV4List(ipRanges);
//        List<String> ipRangesIpV6List = getIpV6List(ipRanges);
//        List<String> responList = new ArrayList<>();
//        //responList.addAll(IPUtil.concatIPRanges(ipRangesIpV4List));
//        List<String> ipV4List = IPUtil.concatIPV4Ranges(ipRangesIpV4List);
//        if(!CollectionUtils.isEmpty(ipV4List)){
//            responList.addAll(ipV4List);
//        }
//        //responList.addAll(IPv6Util.concatIPRanges(ipRangesIpV6List));
//        List<String> ipV6List = IPv6Util.concatIPv6Ranges(ipRangesIpV6List);
//        if(!CollectionUtils.isEmpty(ipV6List)){
//            responList.addAll(ipV6List);
//        }
//        return responList;
//    }
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段,如果存在IPv4、IPv6的混合情况时，IPv4 在前、IPv6在后
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 拼接后的有序连续的IP地址段
//     */
//    public static List<String> concatIPRanges(String... ipRanges) {
//        List<String> ipRangesIpV4List = getIpV4List(ipRanges);
//        List<String> ipRangesIpV6List = getIpV6List(ipRanges);
//        List<String> responList = new ArrayList<>();
//        //responList.addAll(IPUtil.concatIPRanges(ipRangesIpV4List));
//        List<String> ipV4List = IPUtil.concatIPV4Ranges(ipRangesIpV4List);
//        if(!CollectionUtils.isEmpty(ipV4List)){
//            responList.addAll(ipV4List);
//        }
//        //responList.addAll(IPv6Util.concatIPRanges(ipRangesIpV6List));
//        if(!CollectionUtils.isEmpty(IPv6Util.concatIPv6Ranges(ipRangesIpV6List))){
//            responList.addAll(IPv6Util.concatIPv6Ranges(ipRangesIpV6List));
//        }
//        return responList;
//    }
//
//    /**
//     * 将离散的、无序的IP地址段拼接成从小到大排序的连续的IP地址段,如果存在IPv4、IPv6的混合情况时，IPv4 在前、IPv4在后
//     *
//     * @param ipRanges 源IP地址段，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 非重复IP记数
//     */
//    public static long getRangeIPCount(String... ipRanges) {
//        List<String> ipRangesIpV4List = getIpV4List(ipRanges);
//        List<String> ipRangesIpV6List = getIpV6List(ipRanges);
//        long ipV4Length = IPUtil.getRangeIPCount((String[]) ipRangesIpV4List.toArray(new String[ipRangesIpV4List.size()]));
//        //long ipV6Length = IPv6Util.getRangeIPCount((String[]) ipRangesIpV6List.toArray(new String[ipRangesIpV6List.size()]));
//        long ipV6Length = IPv6Util.getRangeIPv6Count(ipRangesIpV6List);
//        return ipV4Length + ipV6Length;
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
//        List<String> ipRangesIpV4List = getIpV4List(ipRanges);
//        List<String> ipRangesIpV6List = getIpV6List(ipRanges);
//        List<String> ipRangesIpV4SubNetsList = IPUtil.changeIPRangesToSubNets(minMaskSize, showMaskSize,
//                ipRangesIpV4List.toArray(new String[ipRangesIpV4List.size()]));
//        List<String> ipRangesIpV6SubNetsList = IPv6Util.changeIPRangesToSubNets(minMaskSize, showMaskSize,
//                ipRangesIpV6List.toArray(new String[ipRangesIpV6List.size()]));
//        List<String> responList = new ArrayList<>();
//        responList.addAll(ipRangesIpV4SubNetsList);
//        responList.addAll(ipRangesIpV6SubNetsList);
//        return responList;
//    }
//
//    /**
//     * 将一组IP地址段拼接，并按起始IP从小到大排序，再转换成点分十进制IPv4 点分十进制反掩码的表示方式
//     *
//     * @param ipRanges 待转换的IP地址范围
//     * @return 转换后的结果
//     */
//    public static List<String> changeIPRangesWithAntiMask(String... ipRanges) {
//        return changeIPRangesWithAntiMask(1, ipRanges);
//    }
//
//    /**
//     * 将一组IP地址段拼接，并按起始IP从小到大排序，再转换成点分十进制IPv4 点分十进制反掩码的表示方式
//     *
//     * @param minMaskSize 允许的最小掩码位数
//     * @param ipRanges    待转换的IP地址范围
//     * @return 转换后的结果
//     */
//    public static List<String> changeIPRangesWithAntiMask(int minMaskSize, String... ipRanges) {
//        if (isIpV6(ipRanges[0])) {
//            return null;
//        }
//        return IPUtil.changeIPRangesWithAntiMask(minMaskSize, ipRanges);
//    }
//
//    /**
//     * 校验IP地址范围是否有交集（保留旧有的方法名）
//     *
//     * @param sourceIP IP范围，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @param targetIP 待校验的IP范围，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数、IPv4-IPv4片段四种格式
//     * @return 有交集返回true，否则返回false
//     */
//    public static boolean validateIPRange(String sourceIP, String targetIP) {
//        if (isIpV6(sourceIP)) {
//            //return IPv6Util.validateIPRange(sourceIP, targetIP);
//            JIPAddress sourceIPAddress = JIPAddressUtils.toIpObject(sourceIP);
//            JIPAddress targetIPAddress = JIPAddressUtils.toIpObject(targetIP);
//            List<JIPAddress> addressList =  JIPAddressIntersecter.intersect(sourceIPAddress, targetIPAddress);
//            return CollectionUtils.isNotEmpty(addressList);
//        }
//        return IPUtil.validateIPRange(sourceIP, targetIP);
//    }
//
//    /**
//     * 根据骨干路由拆分IP网段子网，只有当源IP子网掩码位数大于等于24位且小于等于拆分的子网掩码位数时才进行拆分
//     *
//     * @param sourceIP 源IP，接受点分十进制IPv4、IPv4 掩码、IPv4/掩码位数三种格式
//     * @param route    拆分的子网掩码位数
//     * @return 拆分后的子网网段信息，以逗号分隔，每个子网以点分十进制IPv4 点分十进制掩码的形式表示。未拆分时返回null
//     */
//    public static String calculateSubNets(String sourceIP, int route) {
//
//        if (isIpV6(sourceIP)) {
//            return null;
//        }
//        return IPUtil.calculateSubNets(sourceIP, route);
//    }
//
//    /**
//     * 计算网络地址
//     *
//     * @param ip 接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 三种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 网络地址（即子网起始IP）
//     */
//    public static String getNetworkAddress(String ip) {
//        if (isIpV6(ip)) {
//            return IPv6Util.getNetworkAddress(ip);
//        }
//        return IPUtil.getNetworkAddress(ip);
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
//        if (isIpV6(ip)) {
//            return IPv6Util.getNetworkAddress(ip, mask);
//        }
//        return IPUtil.getNetworkAddress(ip, mask);
//    }
//
//    /**
//     * 根据掩码位数计算掩码
//     *
//     * @param masks 掩码位数
//     * @return 点分十进制表示的掩码
//     */
//    public static String getMask(int masks) {
//        return IPUtil.getMask(masks);
//    }
//
//    /**
//     * 根据掩码位数计算子网大小
//     *
//     * @param mask 掩码数位
//     * @return 子网IP数量
//     */
//    public static long getSubnetSize(int mask) {
//        return IPUtil.getSubnetSize(mask);
//    }
//
//    /**
//     * 计算第一个ip地址，并将其转换为long型数字
//     *
//     * @param ipRange IP地址段，接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 或者 IPv4-IPv4片段 四种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 起始IP的long型数值
//     */
//    public static BigInteger getBeginAddress(String ipRange) {
//        if (isIpV6(ipRange)) {
//            ipRange = convertFormat(ipRange);
//            return IPv6Util.getBeginAddress(ipRange);
//        }
//        return BigInteger.valueOf(IPUtil.getBeginAddress(ipRange));
//    }
//
//    /**
//     * 计算最后一位IP地址，将其转换为long型数字
//     *
//     * @param ipRange IP地址段，接受点分十进制IPv4 或者 IPv4 掩码 或者 IPv4/掩码位数 或者 IPv4-IPv4片段 四种格式，无掩码也无掩码位数的默认为32位掩码
//     * @return 最后一位IP的long型数值
//     */
//    public static BigInteger getEndAddress(String ipRange) {
//        if (isIpV6(ipRange)) {
//            ipRange = convertFormat(ipRange);
//            return IPv6Util.getEndAddress(ipRange);
//        }
//        return BigInteger.valueOf(IPUtil.getEndAddress(ipRange));
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
//        if (isIpV6(ipAddress)) {
//            return IPv6Util.getBeginSubnetAddress(num, ipAddress);
//        }
//        return IPUtil.getBeginSubnetAddress(num, ipAddress);
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
//        if (isIpV6(ip)) {
//            return IPv6Util.getEndSubnetAddress(num, ip);
//        }
//        return IPUtil.getEndSubnetAddress(num, ip);
//    }
//
//    /**
//     * 转换为掩码位数
//     *
//     * @param mask 点分十进制掩码
//     * @return 掩码位数
//     */
//    public static int subMaskToSubNum(String mask) {
//        return IPUtil.subMaskToSubNum(mask);
//    }
//
//    /**
//     * 将10进制整数形式转换成127.0.0.1形式的IP地址
//     *
//     * @param ip IP地址的数值表示
//     * @return 点分十进制表示的IP地址
//     */
//    public static String longToIP(BigInteger ip) {
//        if (ip.compareTo(IPV4_MAX) == 1) {//小于则返回-1，等于则返回0，大于则返回1
//            return IPv6Util.numberToIP(ip);
//        }
//        return IPUtil.longToIP(ip.longValue());
//    }
//
//    public static String numberToIP(BigInteger ip) {
//        return IPv6Util.numberToIP(ip);
//    }
//
//    /**
//     * 将127.0.0.1 形式的IP地址转换成10进制整数
//     *
//     * @param strIP 点分十进制表示的IP地址
//     * @return IP地址的数值表示
//     */
//    public static BigInteger ipToNumber(String strIP) {
//        if (isIpV6(strIP)) {
//            return IPv6Util.ipToNumber(strIP);
//        }
//        return BigInteger.valueOf(IPUtil.ipToLong(strIP));
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
//
//        if (isIpV6(ipRange)) {
//            ipRange = convertFormat(ipRange);
//            //return IPv6Util.getIpList(ipRange);
//            JIPAddress address = JIPAddressUtils.toIpObject(ipRange);
//            List<JIPAddress> addressIPList = JIPAddressUtils.expandIpList(address, 0);
//            return JIPAddressUtils.toIpStringList(addressIPList, IPFormatType.SEGMENT_FULL_FIRST);
//        }
//        return IPUtil.getIpList(ipRange);
//    }
//
//    public static List<String> getIpList(String... ipRange) {
//        List<String> ipList = new LinkedList<>();
//        for (String ip : ipRange) {
//            ipList.addAll(getIpList(ip));
//        }
//        return ipList;
//    }
//
//    public static List<String> getIpList(Collection<String> ipRange) {
//        List<String> ipList = new LinkedList<>();
//        for (String ip : ipRange) {
//            ipList.addAll(getIpList(ip));
//        }
//        return ipList;
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
//    /**
//     * 计算反掩码   255.255.255.0  ==》 0.0.0.255
//     *
//     * @param mask 掩码255.255.255.0
//     * @return 反掩码
//     */
//    public static String getAntiMask(String mask) {
//        if ("0".equals(mask) || "0.0.0.0".equals(mask)) {
//            return "255.255.255.255";
//        }
//        int int_mask = subMaskToSubNum(mask);
//        return getAntiMask(int_mask);
//    }
//
//    /**
//     * 计算反掩码   24  ==》 0.0.0.255
//     *
//     * @param num 掩码位数
//     * @return 反掩码
//     */
//    public static String getAntiMask(int num) {
//        return IPUtil.getAntiMask(num);
//    }
//
//    /**
//     * 验证long型IP地址段起始IP必需小于等于endIP，如不满足则抛出运行时异常
//     *
//     * @param startIP 起始IP
//     * @param endIP   截止IP
//     */
//    private static void checkLongIPRange(long startIP, long endIP) {
//        if (startIP > endIP) {
//            throw new RuntimeException("Invalid IPv4 address range");
//        }
//    }
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
//    /**
//     * 提取IP列表中 IPV4的部分
//     *
//     * @param ipRanges
//     * @return
//     */
//    public static List<String> getIpV4List(Collection<String> ipRanges) {
//        List<String> ipV4List = new ArrayList<>();
//        if(CollectionUtils.isEmpty(ipRanges)) {
//            return ipV4List;
//        }
//        for (String ip : ipRanges) {
//            if (!isIpV6(ip)) {
//                ipV4List.add(ip);
//            }
//        }
//        return ipV4List;
//    }
//
//    /**
//     * 提取IP列表中 IPV6的部分
//     *
//     * @param ipRanges
//     * @return
//     */
//    public static List<String> getIpV6List(Collection<String> ipRanges) {
//        List<String> ipV6List = new ArrayList<>();
//        if(CollectionUtils.isEmpty(ipRanges)) {
//            return ipV6List;
//        }
//        for (String ip : ipRanges) {
//            if (isIpV6(ip)) {
//                ipV6List.add(ip);
//            }
//        }
//        return ipV6List;
//    }
//
//    /**
//     * 提取IP列表中 IPV4的部分
//     *
//     * @param ipRanges
//     * @return
//     */
//    public static List<String> getIpV4List(String... ipRanges) {
//        List<String> ipV4List = new ArrayList<>();
//        for (String ip : ipRanges) {
//            if (!isIpV6(ip)) {
//                ipV4List.add(ip);
//            }
//        }
//        return ipV4List;
//    }
//
//    /**
//     * 提取IP列表中 IPV6的部分
//     *
//     * @param ipRanges
//     * @return
//     */
//    public static List<String> getIpV6List(String... ipRanges) {
//        List<String> ipV6List = new ArrayList<>();
//        for (String ip : ipRanges) {
//            if (isIpV6(ip)) {
//                ipV6List.add(ip);
//            }
//        }
//        return ipV6List;
//    }
//
//    /**
//     * 提取IP列表中 IPV6的部分
//     *
//     * @param ip
//     * @return v4 返回掩码位数， v6返回前缀位数
//     */
//    public static int getSubnetMaskSizeFromIP(String ip) {
//        if (ip.contains("-")) {
//            return 0;
//        }
//        if (ip.contains("/")) {
//            return Integer.valueOf(ip.split("/")[1]);
//        } else if (ip.contains(" ")) {
//            //v4格式
//            return IPUtil.subMaskToSubNum(ip.split(" ")[1]);
//        }
//        if (isIpV6(ip)) {
//            return 128;
//        }
//        return 32;
//    }
//
//    /**
//     * 将ip 设置成xxx/mask 格式
//     *
//     * @param ip
//     * @return
//     */
//    public static String changeIPFormat(String ip) {
//        if (ip.contains("/")) {
//            return ip;
//        }
//        if (isIpV6(ip)) {
//            return ip + "/128";
//        } else if (ip.contains(" ")) {
//            String[] rangeParts = ip.split(" ");
//            return rangeParts[0] + "/" + IPUtil.subMaskToSubNum(rangeParts[1]);
//        } else {
//            return ip + "/32";
//        }
//    }
//
//    public static String getIpv6Abbr(String ip) {
//        JIPAddress address = JIPAddressUtils.toIpObject(ip);
//        List<JIPAddress> addressList = new ArrayList<>();
//        addressList.add(address);
//        return JIPAddressUtils.toIpListString(addressList, IPFormatType.SEGMENT_SIMPLE_FIRST);
//    }
//
//    public static Boolean isSingleIp(String ip){
//        JIPAddress address = JIPAddressUtils.toIpObject(ip);
//        IPType ipType = address != null ? address.getIpType() : IPType.UNKNOWN;
//        return IPType.IPV4.equals(ipType) || IPType.IPV6.equals(ipType);
//    }
//
//    public static String ipFormat(String ip) {
//    	if(isIpV6(ip)) {
//    		return  IPv6Util.IPV6Format(ip);
//    	}
//
//    	return IPUtil.IPV4Format(ip);
//    }
//
//    public static void main(String[] args) {
//    }
//}