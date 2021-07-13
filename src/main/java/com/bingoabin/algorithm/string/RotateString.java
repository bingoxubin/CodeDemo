package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/6 8:14 下午
 */
public class RotateString {
	//NC114 旋转字符串
	//样例：输入"youzan","zanyou" 输出：true
	//分析：给两个字符串，如果能将A从中间某个位置分割为左右两部分字符串（都不为空串），并将左边的字符串移动到右边字符串后面组成新的字符串可以变为字符串B时返回true。
	//思路：将A 复制一份 变成 A+A 如果 这个字符串能包含第二个字符串，那么就返回true
	public static void main(String[] args) {
		String str1 = "youzan";
		String str2 = "zanyou";
		System.out.println(solve(str1, str2));
	}

	public static boolean solve(String A, String B) {
		if (A.length() != B.length()) {
			return false;
		}
		return (A + A).contains(B);
	}
}
