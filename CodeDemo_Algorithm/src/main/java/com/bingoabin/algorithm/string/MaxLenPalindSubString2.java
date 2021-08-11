package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/8/12 12:37 上午
 * @CopyRight: sankuai.com
 * @Description: 最长回文子序列
 */
public class MaxLenPalindSubString2 {
	//Leetcode 516. 最长回文子序列
	//示例：输入：s = "bbbab"  输出：4   解释：一个可能的最长回文子序列为 "bbbb" 。
	//分析：最长的回文子序列  子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
	//思路：
	public static void main(String[] args) {
		String str = "bbbab";
		System.out.println(longestPalindromeSubseq(str));
		System.out.println(longestPalindromeSubseq1(str));
	}

	//动态规划，向右上角靠拢
	public static int longestPalindromeSubseq(String s) {
		int n = s.length();
		// dp 数组全部初始化为 0
		int[][] dp = new int[n][n];
		// base case
		for (int i = 0; i < n; i++)
			dp[i][i] = 1;
		// 反着遍历保证正确的状态转移
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				// 状态转移方程
				if (s.charAt(i) == s.charAt(j))
					dp[i][j] = dp[i + 1][j - 1] + 2;
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}
		// 整个 s 的最长回文子串长度
		return dp[0][n - 1];
	}

	public static int longestPalindromeSubseq1(String s) {
		int len = s.length();
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = 1;
		}
		for (int i = len - 1; i >= 0; i--) {
			for (int j = i + 1; j < len; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][len - 1];
	}
}
