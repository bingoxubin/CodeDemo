package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/6/30 18:37
 */
public class AddAllDigits {
	//Leetcode 258. 各位相加
	//示例：示例:
	//      输入: 38
	//      输出: 2
	//      解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
	//分析：给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
	//思路：
	public static void main(String[] args) {
		AddAllDigits addAllDigits = new AddAllDigits();
		System.out.println(addAllDigits.addDigits(38));
	}

	public int addDigits(int num) {
		while (num > 9) {
			num = getNums(num);
		}
		return num;
	}

	public int getNums(int num) {
		int res = 0;
		while (num > 0) {
			res += num % 10;
			num /= 10;
		}
		return res;
	}
}
