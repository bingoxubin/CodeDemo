package com.bingoabin.newcoder;

/**
 * @author xumaosheng
 * 求一个数组中连续子向量的最大和
 * @date 2019/12/19 16:57
 */
public class _73MaxSubArray {
	public static int maxSubArray(int[] nums) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		if (nums == null || nums.length == 0) {
			return sum;
		}
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			maxSum = Math.max(maxSum, sum);
			if (sum < 0) {
				sum = 0;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] arr = {-1, 4, 2, -2, 3, -1, 2, -3, 5, -6};
		System.out.println(maxSubArray(arr));
	}
}
