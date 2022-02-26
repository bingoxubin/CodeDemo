package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2022/2/26 10:03 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaximumDifference {
    //Leetcode 2016. 增量元素之间的最大差值
    //示例：输入：nums = [1,5,2,10]
    //     输出：9
    //     解释:最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
    //分析：给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
    //     返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
    //思路：
    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 10};
        MaximumDifference maximumDifference = new MaximumDifference();
        System.out.println(maximumDifference.maximumDifference(nums));
    }

    public int maximumDifference(int[] nums) {
        int len = nums.length;
        int res = -1;
        //维护一个最小值
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > min) {
                res = Math.max(res, nums[i] - min);
            }
            min = Math.min(min, nums[i]);
        }
        return res;
    }
}
