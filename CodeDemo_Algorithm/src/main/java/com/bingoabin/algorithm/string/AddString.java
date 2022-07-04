package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/7/4 18:17
 */
public class AddString {
	//Leetcode 415. 字符串相加
	//示例：
	//分析：给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
	//      提示：
	//      num1 和num2 的长度都小于 5100
	//      num1 和num2 都只包含数字 0-9
	//      num1 和num2 都不包含任何前导零
	//      你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
	//思路：
	public static void main(String[] args) {
		AddString addString = new AddString();
		System.out.println(addString.addStrings("111", "999"));
	}

	public String addStrings(String num1, String num2) {
		int len1 = num1.length() - 1;
		int len2 = num2.length() - 1;
		int sum = 0;
		StringBuffer res = new StringBuffer();
		while (sum != 0 || len1 >= 0 || len2 >= 0) {
			int x = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
			int y = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
			int temp = x + y + sum;
			res.append(temp % 10);
			sum = temp / 10;
			len1--;
			len2--;
		}
		return res.reverse().toString();
	}
}
