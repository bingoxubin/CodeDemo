package com.bingoabin.algorithm.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2022/2/9 9:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountKDifference {
    //Leetcode 2006. 差的绝对值为 K 的数对数目
    //示例：输入：nums = [1,2,2,1], k = 1
    //     输出：4
    //     解释：差的绝对值为 1 的数对为：
    //     - [1,2,2,1]
    //     - [1,2,2,1]
    //     - [1,2,2,1]
    //     - [1,2,2,1]
    //分析：给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。
    //     |x|的值定义为：
    //     如果x >= 0，那么值为x。
    //     如果x < 0，那么值为-x。
    //思路：
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1};
        CountKDifference countKDifference = new CountKDifference();
        System.out.println(countKDifference.countKDifference(nums, 1));
    }

    public int countKDifference(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            res += map.getOrDefault(nums[i] - k, 0) + map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return res;
    }
}
