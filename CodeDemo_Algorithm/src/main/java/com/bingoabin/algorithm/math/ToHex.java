package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/7/4 14:39
 */
public class ToHex {
	//Leetcode 405. 数字转换为十六进制数
	//示例：示例 1：
	//      输入:
	//     26
	//     输出:
	//     "1a"
	//     示例 2：
	//     输入:
	//     -1
	//     输出:
	//     "ffffffff"
	//分析：给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
	//     注意:
	//     十六进制中所有字母(a-f)都必须是小写。
	//     十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
	//     给定的数确保在32位有符号整数范围内。
	//     不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
	//思路：
	public static void main(String[] args) {
		ToHex toHex = new ToHex();
		System.out.println(toHex.toHex(26));
		System.out.println(toHex.toHex(-1));
		System.out.println(toHex.toHex(0));
	}

	public String toHex(int num) {
		if(num == 0) return "0";
		StringBuffer res = new StringBuffer();
		char[] hex = "0123456789abcdef".toCharArray();
		while (num != 0) {
			int temp = num & 15;
			res.append(hex[temp]);
			num >>>= 4;
		}
		return res.reverse().toString();
	}
}
