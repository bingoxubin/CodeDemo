package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/6/30 11:18
 */
public class ConvertToTitle {
	//Leetcode 168. Excel表列名称
	//示例：输入: 1
	//      输出: "A"
	//      示例 2:
	//      输入: 28
	//      输出: "AB"
	//分析：给定一个正整数，返回它在 Excel 表中相对应的列名称。
	//      例如，
	//     1 -> A
	//     2 -> B
	//     3 -> C
	//     ...
	//     26 -> Z
	//     27 -> AA
	//     28 -> AB
	//     ...
	//思路：
	public static void main(String[] args) {
		ConvertToTitle convertToTitle = new ConvertToTitle();
		System.out.println(convertToTitle.convertToTitle(27));
	}

	public String convertToTitle(int n) {
		StringBuffer res = new StringBuffer();
		while (n > 0) {
			n--;
			res.append((char) ('A' + n % 26));
			n /= 26;
		}
		return res.toString();
	}
}
