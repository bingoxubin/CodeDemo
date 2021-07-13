package com.bingoabin.algorithm.string;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/6/10 12:31 下午
 */
public class MaxLenCommonSubSequence {
	//NC92 最长公共子序列
	//样例："1A2C3D4B56","B1D23CA45B6A" 输出："123456"   说明:"123456"和“12C4B6”都是最长公共子序列，任意输出一个。
	//分析：两个字符串str1和str2，输出连个字符串的最长公共子序列。如过最长公共子序列为空，则输出-1。
	//思路：动态规划的思想
	public static void main(String[] args) {
		String str1 = "1A2C3D4B56";
		String str2 = "B1D23CA45B6A";
		System.out.println(LCS(str1, str2));
	}

	public static String LCS(String s1, String s2) {
		int s1len = s1.length();
		int s2len = s2.length();
		int[][] dp = new int[s1len + 1][s2len + 1];
		for (int i = 0; i < s1len; i++) {
			for (int j = 0; j < s2len; j++) {
				char char1 = s1.charAt(i);
				char char2 = s2.charAt(j);
				if (char1 == char2) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		System.out.println(Arrays.deepToString(dp));
		if (dp[s1len][s2len] == 0) {
			return "-1";
		}
		StringBuffer res = new StringBuffer();
		int row = s1len;
		int col = s2len;
		while (row >= 1 && col >= 1) {
			if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
				res.append(s1.charAt(row - 1));
				row--;
				col--;
			} else {
				if (dp[row - 1][col] <= dp[row][col - 1]) {
					col--;
				} else {
					row--;
				}
			}
		}
		return res.reverse().toString();
	}
}
