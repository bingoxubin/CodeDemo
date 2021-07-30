package com.bingoabin.algorithm.math;

/**
 * @author xubin34
 * @date 2021/7/30 10:13 上午
 */
public class StringToNumber {
	//Leetcode 171. Excel表列序号
	//示例：A -> 1
	//     B -> 2
	//     C -> 3
	//     ...
	//     Z -> 26
	//     AA -> 27
	//     AB -> 28
	//     ...
	//输入：columnTitle = "AB" 输出 28  字符串最大值：输入: columnTitle = "FXSHRXW"   输出: 2147483647
	//分析：相当于26进制换算，比如AB，先拿到A，在拿到B  结果就是 1 * 26 + 2
	//思路：遍历字符串，进行26进制累加
	public static void main(String[] args) {
		String ans = "FXSHRXW";
		System.out.println(titleToNumber(ans));
	}

	////思路：遍历字符串，进行26进制累加
	public static int titleToNumber(String columnTitle) {
		int res = 0;
		for (char ch : columnTitle.toCharArray()) {
			int temp = ch - 'A' + 1;
			res = res * 26 + temp;
		}
		return res;
	}
}
