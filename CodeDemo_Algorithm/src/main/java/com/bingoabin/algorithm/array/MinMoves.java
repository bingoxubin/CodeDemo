package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/10/20 10:08 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MinMoves {
	//Leetcode 453. 最小操作次数使数组元素相等
	//示例：输入：nums = [1,2,3]
	//     输出：3
	//     解释：
	//     只需要3次操作（注意每次操作会增加两个元素的值）：
	//     1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
	//分析：给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
	//思路：
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		MinMoves minMoves = new MinMoves();
		System.out.println(minMoves.minMoves(arr));
		System.out.println(minMoves.minMoves1(arr));
		System.out.println(minMoves.minMoves2(arr));
		System.out.println(minMoves.minMoves3(arr));
		System.out.println(minMoves.minMoves4(arr));
	}

	//方式一：暴力求解 超时 [1,1000000000]
	public int minMoves1(int[] nums) {
		int min = 0;
		int max = nums.length - 1;
		int count = 0;
		while (true) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < nums[min]) min = i;
				if (nums[i] > nums[max]) max = i;
			}
			if (nums[min] == nums[max]) break;
			for (int i = 0; i < nums.length; i++) {
				if (i != max) {
					nums[i]++;
				}
			}
			count++;
		}
		return count;
	}

	//方式二：暴力优化 超时
	public int minMoves2(int[] nums) {
		int min = 0, max = nums.length - 1, count = 0;
		while (true) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[min] > nums[i]) min = i;
				if (nums[i] > nums[max]) max = i;
			}
			if (nums[min] == nums[max]) break;
			int diff = nums[max] - nums[min];
			count += diff;
			for (int i = 0; i < nums.length; i++) {
				if (i != max) {
					nums[i] += diff;
				}
			}
		}
		return count;
	}

	//方式三：排序
	public int minMoves3(int[] nums) {
		Arrays.sort(nums);
		int result = 0;
		for (int i = nums.length - 1; i > 0; i--) {
			result += nums[i] - nums[0];
		}
		return result;
	}

	//方式四：动态规划
	public int minMoves4(int[] nums) {
		Arrays.sort(nums);
		int moves = 0;
		for (int i = 1; i < nums.length; i++) {
			int diff = (moves + nums[i]) - nums[i - 1];
			nums[i] += moves;
			moves += diff;
		}
		System.out.println(Arrays.toString(nums));
		return moves;
	}

	//方式五：数学法，都变到最小值
	public int minMoves(int[] nums) {
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			count += nums[i];
			min = Math.min(min, nums[i]);
		}
		return count - min * nums.length;
	}

	//自己实现 数学法
	public int minMoves5(int[] nums) {
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			count += nums[i];
			min = Math.min(min, nums[i]);
		}
		return count - min * nums.length;
	}
}
