package com.bingoabin.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xubin34
 * @Date: 2022/3/4 10:00 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SubArrayRanges {
    //Leetcode 2104. 子数组范围和
    //示例：输入：nums = [1,2,3]
    //     输出：4
    //     解释：nums 的 6 个子数组如下所示：
    //     [1]，范围 = 最大 - 最小 = 1 - 1 = 0
    //     [2]，范围 = 2 - 2 = 0
    //     [3]，范围 = 3 - 3 = 0
    //     [1,2]，范围 = 2 - 1 = 1
    //     [2,3]，范围 = 3 - 2 = 1
    //     [1,2,3]，范围 = 3 - 1 = 2
    //     所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
    //分析：给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
    //     返回 nums 中 所有 子数组范围的 和 。
    //     子数组是数组中一个连续 非空 的元素序列。
    //思路：
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        SubArrayRanges subArrayRanges = new SubArrayRanges();
        System.out.println(subArrayRanges.subArrayRanges(nums));
        System.out.println(subArrayRanges.subArrayRanges1(nums));
    }

    int n;

    public long subArrayRanges1(int[] nums) {
        n = nums.length;
        long[] min = getCnt(nums, true), max = getCnt(nums, false);
        long ans = 0;
        for (int i = 0; i < n; i++) ans += (max[i] - min[i]) * nums[i];
        return ans;
    }

    long[] getCnt(int[] nums, boolean isMin) {
        int[] a = new int[n], b = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i]))
                d.pollLast();
            a[i] = d.isEmpty() ? -1 : d.peekLast();
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] > nums[i] : nums[d.peekLast()] < nums[i])) d.pollLast();
            b[i] = d.isEmpty() ? n : d.peekLast();
            d.addLast(i);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) ans[i] = (i - a[i]) * 1L * (b[i] - i);
        return ans;
    }

    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }
}
