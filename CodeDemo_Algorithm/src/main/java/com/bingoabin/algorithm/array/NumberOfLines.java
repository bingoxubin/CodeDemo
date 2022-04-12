package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/4/12 15:59
 */
public class NumberOfLines {
	//Leetcode 806. 写字符串需要的行数
	//示例：示例 1:
	//      输入:
	//      widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
	//      S = "abcdefghijklmnopqrstuvwxyz"
	//      输出: [3, 60]
	//      解释:
	//      所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
	//      我们需要2个整行和占用60个单位的一行。
	//分析：我们要把给定的字符串 S从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。
	//      现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
	//思路：
	public static void main(String[] args) {
		int[] widths = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		String s = "abcdefghijklmnopqrstuvwxyz";
		NumberOfLines numberOfLines = new NumberOfLines();
		System.out.println(Arrays.toString(numberOfLines.numberOfLines(widths, s)));
		System.out.println(Arrays.toString(numberOfLines.numberOfLines1(widths, s)));
	}

	public int[] numberOfLines(int[] widths, String s) {
		int sum = 0;
		int lines = 1;
		for (int i = 0; i < s.length(); i++) {
			sum += widths[s.charAt(i) - 'a'];
			if (sum > 100) {
				sum -= widths[s.charAt(i) - 'a'];
				sum = 0;
				sum += widths[s.charAt(i) - 'a'];
				lines++;
			}
		}
		return new int[]{lines, sum};
	}

	public int[] numberOfLines1(int[] widths, String s) {
		int sum = 0;
		int row = 1;
		for (int i = 0; i < s.length(); i++) {
			sum += widths[s.charAt(i) - 'a'];
			if (sum > 100) {
				sum = widths[s.charAt(i) - 'a'];
				row++;
			}
		}
		return new int[]{row, sum};
	}
}
