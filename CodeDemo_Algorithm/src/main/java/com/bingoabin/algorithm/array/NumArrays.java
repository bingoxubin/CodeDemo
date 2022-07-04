package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2022/7/4 10:43
 */
public class NumArrays {
	//Leetcode 303. 区域和检索 - 数组不可变
	//示例：示例：
	//     输入：
	//     ["NumArray", "sumRange", "sumRange", "sumRange"]
	//     [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
	//     输出：
	//     [null, 1, -1, -3]
	//分析：给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
	//     实现 NumArray 类：
	//     NumArray(int[] nums) 使用数组 nums 初始化对象
	//     int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
	//思路：
	public static void main(String[] args) {
		int[] arrays = {-2, 0, 3, -5, 2, -1};
		NumArray numArrays = new NumArray(arrays);
		System.out.println(numArrays.sumRange(0, 2));
		System.out.println(numArrays.sumRange(2, 5));
		System.out.println(numArrays.sumRange(0, 5));
	}
}

class NumArray {
	int[] sums;

	public NumArray(int[] nums) {
		sums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			sums[i + 1] = sums[i] + nums[i];
		}
	}

	public int sumRange(int i, int j) {
		return sums[j + 1] - sums[i];
	}
}
