package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/12/1 12:23 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxPower {
	//Leetcode 1446. 连续字符
	//示例:输入：s = "abbcccddddeeeeedcba"  输出：5
	//    解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
	//分析:给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。请你返回字符串的能量。
	//思路:
	public static void main(String[] args) {
		String s = "abbcccddddeeeeedcba";
		MaxPower maxPower = new MaxPower();
		System.out.println(maxPower.maxPower(s));
	}

	public int maxPower(String s) {
		//记录最长的长度
		int max = 1;
		//记录当前的长度
		int cur = 1;
		for (int i = 1; i < s.length(); i++) {
			//当前字符跟前一个字符相等的话，cur++  统计最大的max
			if (s.charAt(i) == s.charAt(i - 1)) {
				cur++;
				max = Math.max(cur, max);
			} else {
				//如果不相等的话  cur恢复到1
				cur = 1;
			}
		}
		return max;
	}
}
