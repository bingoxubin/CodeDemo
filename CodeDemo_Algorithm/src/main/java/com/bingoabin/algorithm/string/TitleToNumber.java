package com.bingoabin.algorithm.string;

/**
 * @author bingoabin
 * @date 2022/6/30 11:28
 */
public class TitleToNumber {
	//Leetcode 171. Excel表列序号
	//示例：示例 1:
	//     输入: "A"
	//     输出: 1
	//     示例 2:
	//     输入: "AB"
	//     输出: 28
	//分析：给定一个Excel表格中的列名称，返回其相应的列序号。
	//     例如，
	//     A -> 1
	//     B -> 2
	//     C -> 3
	//     ...
	//     Z -> 26
	//     AA -> 27
	//     AB -> 28
	//     ...
	//思路：
	public static void main(String[] args) {
		TitleToNumber titleToNumber = new TitleToNumber();
		System.out.println(titleToNumber.titleToNumber("AA"));
	}

	public int titleToNumber(String s) {
		int res = 0;
		for (char ch : s.toCharArray()) {
			int num = (int) (ch - 'A' + 1);
			res = res * 26 + num;
		}
		return res;
	}
}

