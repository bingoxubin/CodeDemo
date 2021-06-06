package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/6 11:41 上午
 */
public class MaxLenCommSubString {
	//NC127 最长公共子串
	//样例："1AB2345CD","12345EF" 输出：2 3 4 5
	//分析：给定两个字符串，求出两个字符串之间的最长公共子串
	//思路：采用string的substring，从string1中从第一个字符开始遍历，如果在string2中包含这个，记录下最大长度，以及开始下标，那么end++,否则start++，不断遍历
	public static void main(String[] args) {
		String str1 = "1AB2345CD";
		String str2 = "12345EF";
		System.out.println(LCS(str1, str2));
	}

	//采用string的substring，从string1中从第一个字符开始遍历，如果在string2中包含这个，记录下最大长度，以及开始下标，那么end++,否则start++，不断遍历
	public static String LCS(String str1, String str2) {
		int maxlen = 0;
		int index = 0;
		int start = 0;
		int end = 1;
		while (end <= str1.length()) {
			if (str2.contains(str1.substring(start, end))) {
				if (end - start > maxlen) {
					maxlen = end - start;
					index = start;
				}
				end++;
			} else {
				start++;
			}
		}
		return str1.substring(index, index + maxlen);
	}
}
