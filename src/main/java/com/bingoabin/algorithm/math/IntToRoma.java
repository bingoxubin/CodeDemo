package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/14 1:49 下午
 */
public class IntToRoma {
	//LeetCode.12 整数转罗马数字 https://leetcode-cn.com/problems/integer-to-roman/
	//案例：输入3  输出III
	//分析：将4000以内(不包括4000)的数字转化为roma数字，需要记住规则
	// I             1
	// V             5   IV:4
	// X             10  IX:9
	// L             50  XL:40
	// C             100  XC:90
	// D             500  CD:400
	// M             1000 CM:900
	//思路：先用数组分别记下个十百千的值，然后用目标值除以进位，进行求解
	public static void main(String[] args) {
		System.out.println(intToRoman(3999));
	}

	public static String intToRoman(int nums) {
		String[] thousands = {"", "M", "MM", "MMM"};
		String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		return thousands[nums / 1000] + hundreds[nums % 1000 / 100] + tens[nums % 100 / 10] + ones[nums % 10];
	}
}
