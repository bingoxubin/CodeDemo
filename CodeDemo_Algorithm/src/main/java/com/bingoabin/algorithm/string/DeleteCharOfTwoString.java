package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/9/25 11:15 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DeleteCharOfTwoString {
	//Leetcode 583. 两个字符串的删除操作
	//示例：输入: "sea", "eat"   输出: 2  解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
	//分析：给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
	//思路：用 MaxLenCommSubSequence.java中的方式 进行求解
	public static void main(String[] args) {
		String str1 = "sea";
		String str2 = "eat";
		DeleteCharOfTwoString deleteCharOfTwoString = new DeleteCharOfTwoString();
		System.out.println(deleteCharOfTwoString.minDistance(str1, str2));
	}

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c1 = word1.charAt(i);
				char c2 = word2.charAt(j);
				if (c1 == c2) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}
		int len = dp[m][n];
		return word1.length() + word2.length() - 2 * len;
	}
}
