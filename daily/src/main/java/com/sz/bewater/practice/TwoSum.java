package com.sz.bewater.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: practice
 * Description: 两数之和 返回下标数组
 *
 * @Author: zhoudun
 * @Date: 2025/5/30
 */
public class TwoSum {


    public int[] twoSum(int[] nums, int target) {
        //空间换时间 以map 来接受遍历过的数据
        // 然后判断 map 中是否包含满足与现遍历到的数组中的元素之和为 target 满足则返回 不满足加到 map 中
        Map<Integer, Integer> map = new HashMap<>();    //key 为数组的值 value 为下标
        for (int i = 0; i < nums.length; i++) {
            int y = target - nums[i];
            if (map.containsKey(y)) {
                return new int[]{map.get(y), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};

    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 19;
        TwoSum twoSum = new TwoSum();

        int[] result = twoSum.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
