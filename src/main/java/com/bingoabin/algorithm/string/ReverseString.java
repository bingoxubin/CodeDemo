package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/6 6:24 下午
 */
public class ReverseString {
	//NC103 翻转字符串
	//样例：输入"abcd" 输出：dcba
	//分析：将字符串进行反转
	//思路：将字符串转化成char数组，然后进行首尾交换
	public static void main(String[] args) {
		String res = "abcd";
		System.out.println(solve(res));
	}

	public static String solve(String str) {
		char[] res = str.toCharArray();
		int left = 0, right = res.length - 1;
		while (left < right) {
			char temp = res[left];
			res[left] = res[right];
			res[right] = temp;
			left++;
			right--;
		}
		// return new String(res);
		return String.valueOf(res);
	}
}
