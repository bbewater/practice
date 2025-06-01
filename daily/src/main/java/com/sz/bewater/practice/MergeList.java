package com.sz.bewater.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: practice
 * Description: 两个升序列表合并为一个新的升序列表返回
 *
 * @Author: zhoudun
 * @Date: 2025/5/30
 */
public class MergeList {



    public List<Integer> mergeTwoLists(List<Integer> list1,List<Integer> list2){
        List<Integer> merged = new ArrayList<>();

        if (list1 != null) {
            merged.addAll(list1);
        }
        if (list2 != null) {
            merged.addAll(list2);
        }

        Collections.sort(merged);

        return merged;


    }
}
