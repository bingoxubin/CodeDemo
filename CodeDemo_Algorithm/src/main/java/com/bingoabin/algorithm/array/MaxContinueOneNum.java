package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2022/7/7 10:34
 */
public class MaxContinueOneNum {
	//Leetcode 485. 最大连续1的个数
	//示例：示例 1:
	//      输入: [1,1,0,1,1,1]
	//      输出: 3
	//      解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
	//      注意：
	//      输入的数组只包含 0 和1。
	//      输入数组的长度是正整数，且不超过 10,000。
	//分析：给定一个二进制数组， 计算其中最大连续1的个数。
	//思路：
	public static void main(String[] args) {
		MaxContinueOneNum maxContinueOneNum = new MaxContinueOneNum();
		int[] nums = {1, 1, 0, 1, 1, 1};
		System.out.println(maxContinueOneNum.findMaxConsecutiveOnes(nums));
	}

	public int findMaxConsecutiveOnes(int[] nums) {
		int count = 0;
		int maxCount = 0;
		for (int num : nums) {
			if (num == 1) {
				count++;
			} else {
				maxCount = Math.max(count, maxCount);
				count = 0;
			}
		}
		return Math.max(count, maxCount);
	}
}
