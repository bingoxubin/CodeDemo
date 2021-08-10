package com.bingoabin.algorithm.dp;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/8/11 1:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ArithmeticArraySlices2 {
	//Leetcode 446. 等差数列划分 II - 子序列
	//示例：输入：nums = [2,4,6,8,10]
	//     输出：7
	//     解释：所有的等差子序列为：
	//     [2,4,6]
	//     [4,6,8]
	//     [6,8,10]
	//     [2,4,6,8]
	//     [4,6,8,10]
	//     [2,4,6,8,10]
	//     [2,6,10]
	//分析：给一个数组，求出所有等差数组的子序列个数，需要连续的至少3个元素  【子序列】
	//思路：
	public static void main(String[] args) {
		int[] arr = {2, 4, 6, 8, 10};
		System.out.println(numberOfArithmeticSlices(arr));
	}

	public static int numberOfArithmeticSlices(int[] nums) {
		int len = nums.length;
		int res = 0;
		HashMap<Long, Integer>[] arr = new HashMap[len];
		for (int i = 0; i < len; i++) {
			arr[i] = new HashMap<>();
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < i; j++) {
				long div = 1L * nums[i] - nums[j];
				int cnt = arr[j].getOrDefault(div, 0);
				res += cnt;
				arr[i].put(div, arr[i].getOrDefault(div, 0) + cnt + 1);
			}
		}
		return res;
	}
}
