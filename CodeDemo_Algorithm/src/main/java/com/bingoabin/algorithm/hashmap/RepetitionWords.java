package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2022/1/20 9:45 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RepetitionWords {
    //Leetcode 219. 存在重复元素 II
    //示例：输入：nums = [1,2,3,1], k = 3  输出：true
    //分析：给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
    //思路：
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        RepetitionWords repetitionWords = new RepetitionWords();
        System.out.println(repetitionWords.containsNearbyDuplicate(nums, 3));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
