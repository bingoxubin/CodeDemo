package com.bingoabin.algorithm.dp;

/**
 * @Author: xubin34
 * @Date: 2021/9/20 5:17 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxUpSubArrayNums {
	//Leetcode 73. 最长递增子序列的个数
	//案例：输入: [1,3,5,4,7]  输出: 2   解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
	//分析：给定一个未排序的整数数组，找到最长递增子序列的个数。
	//思路：动态规划
	public static void main(String[] args) {
		MaxUpSubArrayNums maxUpSubArrayNums = new MaxUpSubArrayNums();
		int[] arr = {1, 3, 5, 4, 7};
		System.out.println(maxUpSubArrayNums.findNumberOfLIS(arr));
		System.out.println(maxUpSubArrayNums.findNumberOfLIS1(arr));

	}

	//lens[i] 为考虑以 nums[i]nums[i] 为结尾的最长上升子序列的长度。
	//counts[i] 为考虑以 nums[i]nums[i] 结尾的最长上升子序列的个数。
	public int findNumberOfLIS(int[] nums) {
		int n = nums.length;
		int[] lens = new int[n];
		int[] counts = new int[n];
		int max = 1;
		for (int i = 0; i < n; i++) {
			lens[i] = counts[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					if (lens[i] < lens[j] + 1) {
						lens[i] = lens[j] + 1;
						counts[i] = counts[j];
					} else if (lens[i] == lens[j] + 1) {
						counts[i] += counts[j];
					}
				}
			}
			//记录最大长度
			max = Math.max(max, lens[i]);
		}
		//遍历counts  如果是最大长度  那么ans + counts
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (lens[i] == max) ans += counts[i];
		}
		return ans;
	}

	//自己实现一下
	public int findNumberOfLIS1(int[] nums) {
		int n = nums.length;
		int[] lens = new int[n];
		int[] counts = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			lens[i] = counts[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					if (lens[i] < lens[j] + 1) {
						lens[i] = lens[j] + 1;
						counts[i] = counts[j];
					} else if (lens[i] == lens[j] + 1) {
						counts[i] += counts[j];
					}
				}
			}
			max = Math.max(max, lens[i]);
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (lens[i] == max) {
				ans += counts[i];
			}
		}
		return ans;
	}
}
