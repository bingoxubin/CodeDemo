package com.bingoabin.algorithm.slipwindow;

import java.util.Arrays;

/**
 * @author xubin34
 * @date 2021/7/19 2:10 上午
 */
public class MaxFrequency {
	//LeetCode 1838. 最高频元素的频数
	//示例：输入：nums = [1,2,4], k = 5  输出：3
	//分析：一个数组和k，一步操作中可以将数组中任意一个值+1，最多执行k次，最后数组中的最高频次的个数
	//思路：先对数组进行排序，通过滑动窗口来实现，定义左边界0，右边界1，求出在窗口中所需要的次数，如果窗口所需要的次数大于了k 那么左边界左移，
	// 窗口的值减掉两个差值，否则找出最大值，右边界右移
	public static void main(String[] args) {
		int[] nums = {1, 2, 4};
		System.out.println(maxFrequency(nums, 5));
	}

	//思路：先对数组进行排序，通过滑动窗口来实现，定义左边界0，右边界1，求出在窗口中所需要的次数，如果窗口所需要的次数大于了k 那么左边界左移，
	// 窗口的值减掉两个差值，否则找出最大值，右边界右移
	public static int maxFrequency(int[] nums, int k) {
		Arrays.sort(nums);
		int len = nums.length;
		int res = 1;
		int window = 0;
		int i = 0;
		int j = 1;
		while (j < len) {
			window += (nums[j] - nums[j - 1]) * (j - i);  //在窗口内所需要的次数
			while (window > k) {   //如果窗口所需要的次数大于了k 那么左边界左移，窗口的值减掉两个差值
				window -= nums[j] - nums[i];
				i++;
			}
			res = Math.max(res, j - i + 1);  //找出最大值
			j++; //右边界右移
		}
		return res;
	}
}
