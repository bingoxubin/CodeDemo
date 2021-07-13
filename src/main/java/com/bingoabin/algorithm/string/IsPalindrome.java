package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/7 1:56 下午
 */
public class IsPalindrome {
	//NC141 判断回文
	//样例：输入"absba" 输出：true
	//分析：判断该字符串是否回文
	//思路:从字符串的两边向中间比较
	public static void main(String[] args) {
		String str = "absba";
		System.out.println(judge(str));
	}

	public static boolean judge(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
