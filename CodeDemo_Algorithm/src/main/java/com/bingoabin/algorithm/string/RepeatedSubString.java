package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/7/6 16:47
 */
public class RepeatedSubString {
	//Leetcode 459. 重复的子字符串
	//示例：示例 1:
	//      输入: "abab"
	//      输出: True
	//      解释: 可由子字符串 "ab" 重复两次构成。
	//      示例 2:
	//      输入: "aba"
	//      输出: False
	//      示例 3:
	//      输入: "abcabcabcabc"
	//      输出: True
	//      解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
	//分析：给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
	//思路：
	public static void main(String[] args) {
		RepeatedSubString repeatedSubString = new RepeatedSubString();
		System.out.println(repeatedSubString.repeatedSubstringPattern("abcabcabcabc"));
		System.out.println(repeatedSubString.repeatedSubstringPattern1("abcabcabcabc"));
		System.out.println(repeatedSubString.repeatedSubstringPattern1("aba"));
	}

	public boolean repeatedSubstringPattern(String s) {
		int len = s.length();
		for (int i = 1; i * 2 <= len; i++) {
			if (len % i == 0) {
				boolean match = true;
				for (int j = i; j < len; j++) {
					if (s.charAt(j) != s.charAt(j - i)) {
						match = false;
					}
				}
				if (match) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean repeatedSubstringPattern1(String s) {
		return (s + s).indexOf(s, 1) != s.length();
	}
}
