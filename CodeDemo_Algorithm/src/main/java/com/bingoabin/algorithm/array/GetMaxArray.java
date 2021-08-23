package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/8/23 10:26 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class GetMaxArray {
	//Leetcode 1646. 获取生成数组中的最大值
	//示例：n = 7时，结果为3
	//分析：给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
	// nums[0] = 0
	// nums[1] = 1
	// 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
	// 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
	// 返回生成数组 nums 中的 最大 值。
	//思路：通过演练即可
	public static void main(String[] args) {
		System.out.println(getMaximumGenerated(7));
	}

	public static int getMaximumGenerated(int n) {
		int[] result = new int[n + 1];
		if (n <= 1) return n;
		result[0] = 0;
		result[1] = 1;
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if ((i & 1) == 0) {  //i为偶数
				result[i] = result[i / 2];
			} else { //为奇数
				result[i] = result[i / 2] + result[i / 2 + 1];
			}
			count = Math.max(count, result[i]);
		}
		return count;
	}
}
