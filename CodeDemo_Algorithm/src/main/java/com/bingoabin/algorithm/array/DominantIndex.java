package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2022/1/13 12:27 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DominantIndex {
    //Leetcode 747. 至少是其他数字两倍的最大数
    //示例：输入：nums = [3,6,1,0]
    //     输出：1
    //     解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
    //分析：给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
    //     请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
    //思路：
    public static void main(String[] args) {
        int[] nums = {3, 6, 1, 0};
        DominantIndex dominantIndex = new DominantIndex();
        System.out.println(dominantIndex.dominantIndex(nums));
    }

    //先求出最大值的下标，然后迭代判断是否满足值
    public int dominantIndex(int[] nums) {
        int max = nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != index && nums[i] * 2 > nums[index]) {
                return -1;
            }
        }
        return index;
    }
}
