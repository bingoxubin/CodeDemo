package com.bingoabin.algorithm.slipwindow;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/2/11 9:28 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MinimumDifference {
    //Leetcode 1984. 学生分数的最小差值
    //示例:输入：nums = [9,4,1,7], k = 2
    //    输出：2
    //    解释：选出 2 名学生的分数，有 6 种方法：
    //    - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
    //    - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
    //    - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
    //    - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
    //    - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
    //    - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
    //    可能的最小差值是 2
    //分析：给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
    //     从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
    //     返回可能的 最小差值 。
    //
    //思路：排序 + 滑动指针
    public static void main(String[] args) {
        int[] nums = {9, 4, 1, 7};
        MinimumDifference minimumDifference = new MinimumDifference();
        System.out.println(minimumDifference.minimumDifference(nums, 2));
    }

    //排序 + 滑动窗口
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = nums[k - 1] - nums[0];
        for (int i = k; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);
        }
        return ans;
    }
}
