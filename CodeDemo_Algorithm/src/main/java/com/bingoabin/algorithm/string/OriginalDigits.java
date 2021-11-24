package com.bingoabin.algorithm.string;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/11/24 11:33 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class OriginalDigits {
	//Leetcode 423. 从英文中重建数字
	//示例：输入：s = "owoztneoer"  输出："012"
	//分析：给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
	//思路：
	public static void main(String[] args) {
		String s = "owoztneoer";
		OriginalDigits originalDigits = new OriginalDigits();
		System.out.println(originalDigits.originalDigits(s));
	}

	public String originalDigits(String s) {
		String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};
		int n = s.length();
		int[] cnts = new int[26];
		for (int i = 0; i < n; i++) cnts[s.charAt(i) - 'a']++;
		StringBuilder sb = new StringBuilder();
		for (int i : priority) {
			int k = Integer.MAX_VALUE;
			for (char c : ss[i].toCharArray()) k = Math.min(k, cnts[c - 'a']);
			for (char c : ss[i].toCharArray()) cnts[c - 'a'] -= k;
			while (k-- > 0) sb.append(i);
		}
		char[] cs = sb.toString().toCharArray();
		Arrays.sort(cs);
		return String.valueOf(cs);
	}
}
