package com.bingoabin.algorithm.arraytree;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/4/4 3:05 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumArray {
    //Leetcode 307. 区域和检索 - 数组可修改
    //示例：输入：
    //     ["NumArray", "sumRange", "update", "sumRange"]
    //     [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
    //     输出：
    //     [null, 9, null, 8]
    //     解释：
    //     NumArray numArray = new NumArray([1, 3, 5]);
    //     numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
    //     numArray.update(1, 2);   // nums = [1,2,5]
    //     numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
    //分析：给你一个数组 nums ，请你完成两类查询。
    //     其中一类查询要求 更新 数组nums下标对应的值
    //     另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的 和，其中left <= right
    //     实现 NumArray 类：
    //     NumArray(int[] nums) 用整数数组 nums 初始化对象
    //     void update(int index, int val) 将 nums[index] 的值 更新 为 val
    //     int sumRange(int left, int right) 返回数组nums中索引left和索引right之间（包含）的nums元素的 和（即，nums[left] + nums[left + 1], ..., nums[right]）
    //思路：
    public static void main(String[] args) {
        System.out.println(1 & (-1));  // 1
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        NumArray numArray = new NumArray(nums);
        System.out.println(Arrays.toString(numArray.tree));
        //[0, 1, 3, 3, 10, 5, 11, 7, 36, 9, 19]
        //从1开始算  数字1  加到了 1，2，4，8上
        //         数字2  加到了 2，4，8
        //         数字3  加到了 3，4，8
        //         数字4  加到了 4，8
        //         数字5  加到了 5，6，8
        //         数字6  加到了 6，8
        //         数字7  加到了 7，8
        //         数字8  加到了 8
        //         数字9  加到了 9，10
        //         数字10 加到了 10
        System.out.println(numArray.query(7));
        System.out.println(numArray.sumRange(5,7));
    }

    //数状数组
    int[] tree;

    public int lowbit(int x) {
        return x & (-x);
    }

    //在树状数组 x 位置中增加值val
    public void add(int x, int val) {
        for (int i = x; i <= len; i += lowbit(i)) {
            tree[i] += val;
        }
    }

    //在树状数组中 查询前缀和的方法
    public int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    int len;
    int[] nums;

    //提供设计的三个方法
    //初始化
    public NumArray(int[] _nums) {
        len = _nums.length;
        nums = _nums;
        tree = new int[len + 1];
        for (int i = 0; i < len; i++) {
            add(i + 1, nums[i]);
        }
    }

    //更新值
    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    //求区间和
    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }
}
