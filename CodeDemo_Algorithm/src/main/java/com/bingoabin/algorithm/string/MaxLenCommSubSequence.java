package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/9/25 11:05 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxLenCommSubSequence {
	//最长公共子序列
	//text1="abcde",text2="ace"
	public static void main(String[] args) {
		String str1 = "abcde";
		String str2 = "ace";
		MaxLenCommSubSequence maxLenCommSubSequence = new MaxLenCommSubSequence();
		System.out.println(maxLenCommSubSequence.longestCommonSubsequence(str1, str2));
	}

	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c1 = text1.charAt(i);
				char c2 = text2.charAt(j);
				if (c1 == c2) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}
		return dp[m][n];
	}
}
