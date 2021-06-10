package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/10 1:02 下午
 */
public class WildcardCharacter2 {
	//NC122 正则表达式匹配
	//样例：输入"",".*" 输出：true
	//分析：匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
	//思路：
	public static void main(String[] args) {
		String str1 = "";
		String str2 = ".*";
		WildcardCharacter2 wildcardCharacter2 = new WildcardCharacter2();
		System.out.println(wildcardCharacter2.isMatch(str1, str2));
	}

	public boolean match(char[] str, char[] pattern) {
		int m = str.length;
		int n = pattern.length;

		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int i = 0; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (pattern[j - 1] == '*') {
					f[i][j] = f[i][j - 2];
					if (matches(str, pattern, i, j - 1)) {
						f[i][j] = f[i][j] || f[i - 1][j];
					}
				} else {
					if (matches(str, pattern, i, j)) {
						f[i][j] = f[i - 1][j - 1];
					}
				}
			}
		}
		return f[m][n];
	}

	public boolean matches(char[] s, char[] p, int i, int j) {
		if (i == 0) {
			return false;
		}
		if (p[j - 1] == '.') {
			return true;
		}
		return s[i - 1] == p[j - 1];
	}

	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();

		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int i = 0; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (p.charAt(j - 1) == '*') {
					f[i][j] = f[i][j - 2];
					if (matches(s, p, i, j - 1)) {
						f[i][j] = f[i][j] || f[i - 1][j];
					}
				} else {
					if (matches(s, p, i, j)) {
						f[i][j] = f[i - 1][j - 1];
					}
				}
			}
		}
		return f[m][n];
	}

	public boolean matches(String s, String p, int i, int j) {
		if (i == 0) {
			return false;
		}
		if (p.charAt(j - 1) == '.') {
			return true;
		}
		return s.charAt(i - 1) == p.charAt(j - 1);
	}
}
