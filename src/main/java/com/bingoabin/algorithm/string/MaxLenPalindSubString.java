package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/10 12:48 下午
 */
public class MaxLenPalindSubString {
	//NC17 最长回文子串
	//样例：输入"abc1234321ab",12 输出：7
	//分析：给一个字符串，求出计算其中最长回文子串的长度。
	//思路:
	public static void main(String[] args) {
		String str = "abc1234321ab";
		MaxLenPalindSubString maxLenPalindSubString = new MaxLenPalindSubString();
		System.out.println(maxLenPalindSubString.getLongestPalindrome(str, 12));
		System.out.println(maxLenPalindSubString.getLongestPalindrome1(str, 12));
		System.out.println(maxLenPalindSubString.getLongestPalindrome2(str, 12));
		System.out.println(maxLenPalindSubString.getLongestPalindrome3(str, 12));
	}

	//Manacher算法
	public int getLongestPalindrome1(String A, int n) {
		if (A.length() < 0) return A.length();
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < A.length(); i++) {
			buff.append("#").append(A.charAt(i));
		}
		buff.append("#");

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
		return maxLen;
	}

	//动态规划
	public int getLongestPalindrome2(String A, int n) {
		if (A.length() < 2) {
			return A.length();
		}
		boolean[][] helper = new boolean[A.length()][A.length()];
		char[] charArr = A.toCharArray();
		int maxLen = 1, begin = 0;
		for (int j = 1; j < A.length(); j++) {
			for (int i = 0; i < j; i++) {
				if (charArr[i] != charArr[j]) {
					helper[i][j] = false;
				} else {
					if (j - i < 3) {
						helper[i][j] = true;
					} else {
						helper[i][j] = helper[i + 1][j - 1];
					}
				}

				if (helper[i][j] == true && j - i + 1 > maxLen) {
					begin = i;
					maxLen = j - i + 1;
				}
			}
		}
		//return s.substring(begin,begin+maxLen);
		return maxLen;
	}

	//暴力
	public int getLongestPalindrome3(String A, int n) {
		if (A.length() < 2) {
			return A.length();
		}
		int maxLen = 1;
		int begin = 0;
		char[] charArray = A.toCharArray();
		for (int i = 0; i < A.length() - 1; i++) {
			for (int j = i + 1; j < A.length(); j++) {
				if (maxLen < j - i + 1 && validStr(charArray, i, j)) {
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		//return s.substring(begin,begin+maxLen);
		return maxLen;
	}

	public boolean validStr(char[] s, int left, int right) {
		while (left < right) {
			if (s[left] != s[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	//中心扩展法
	public int getLongestPalindrome(String A, int n) {
		if (A.length() < 2) {
			return A.length();
		}
		int maxLen = 1;
		String result = A.substring(0, 1);
		for (int i = 0; i < A.length() - 1; i++) {
			String maxA = centerSpread(A, i, i);
			String maxB = centerSpread(A, i, i + 1);
			String temp = maxA.length() > maxB.length() ? maxA : maxB;
			if (temp.length() > maxLen) {
				maxLen = temp.length();
				result = temp;
			}
		}
		return maxLen;
	}

	public String centerSpread(String s, int i, int j) {
		while (i >= 0 && j < s.length()) {
			if (s.charAt(i) == s.charAt(j)) {
				i--;
				j++;
			} else {
				break;
			}
		}
		return s.substring(i + 1, j);
	}
}
