package com.bingoabin.test;

import java.util.regex.Pattern;

/**
 * @author bingoabin
 * @date 2022/8/30 18:23
 * @Description: 通配符
 */
public class IsMatchIng {
	public static void main(String[] args) {
		System.out.println(new IsMatchIng().isMatch("test_12", "test_*"));
		System.out.println(Pattern.compile("test").matcher("test").matches());
		System.out.println(Pattern.compile("\\*").matcher("t").matches());

		System.out.println("t".matches("\\*"));
	}

	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; ++i) {
			if (p.charAt(i - 1) == '*') {
				dp[0][i] = true;
			} else {
				break;
			}
		}
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
				} else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				}
			}
		}
		return dp[m][n];
	}
}
