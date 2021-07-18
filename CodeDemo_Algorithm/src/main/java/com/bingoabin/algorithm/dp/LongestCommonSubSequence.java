package com.bingoabin.algorithm.dp;

/**
 * @author xubin03
 * @date 2021/5/21 12:49 上午
 */
public class LongestCommonSubSequence {
	//LeetCode.1035 不相交的线  https://leetcode-cn.com/problems/uncrossed-lines/
	//样例：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]  输出 3
	//分析：两个数组  上下排列  相同的数字相互连接，求不能相交，能组成的最大的对数
	//思路：该问题能转化成两个数组的最长公共子序列问题
	public static void main(String[] args) {
		int[] arr1 = {2, 5, 1, 2, 5};
		int[] arr2 = {10, 5, 2, 1, 5, 2};
		System.out.println(maxUncrossedLines(arr1, arr2));
	}

	//定义dp数组，先定为第一个数组的第一个元素i-1开始，然后跟第二个数组从头开始匹配，如果相等，那么最长子序列为dp[i-1][j-1] + 1,如果不相等，就是前面一个数字的最大值Math.max(dp[i - 1][j], dp[i][j - 1])
	public static int maxUncrossedLines(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			int num1 = nums1[i - 1];
			for (int j = 1; j <= n; j++) {
				int num2 = nums2[j - 1];
				if (num1 == num2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}
}
