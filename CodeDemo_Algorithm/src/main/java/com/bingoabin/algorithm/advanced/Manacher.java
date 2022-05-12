package com.bingoabin.algorithm.advanced;

/**
 * @author bingoabin
 * @date 2022/4/23 19:15
 */
public class Manacher {
	public static void main(String[] args) {
		String str = "abcafsaddasdf";
		System.out.println(longestPalindrome(str));
	}

	public static String longestPalindrome(String s) {
		if (s.length() < 0) return s;
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			buff.append("#").append(s.charAt(i));
		}
		buff.append("#");
		System.out.println(buff);

		int c = 0, r = 0;
		int maxLen = 1, maxCen = 0;
		int[] p = new int[buff.length()];
		for (int i = 0; i < buff.length(); i++) {
			p[i] = i < r ? Math.min(r - i, p[2 * c - i]) : 0;
			while (i - p[i] >= 0 && i + p[i] < buff.length() && buff.charAt(i + p[i]) == buff.charAt(i - p[i])) {
				p[i]++;
			}
			if (i > r) {
				c = i;
				r = p[i] + i;
			}
			if (p[i] - 1 > maxLen) {
				maxLen = p[i] - 1;
				maxCen = i;
			}
		}
		int begin = (maxCen - maxLen) / 2;
		return s.substring(begin, begin + maxLen);
	}
}
