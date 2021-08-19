package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/8/20 2:04 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ReverseString2 {
	//Leetcode 541. 反转字符串 II
	//案例：输入：s = "abcdefg", k = 2 输出："bacdfeg"
	//分析：给一个字符串，和一个k，实现每2k个字符串长度，翻转前面k个
	//思路：演绎即可
	public static void main(String[] args) {
		String res = "abcdefg";
		ReverseString2 ans = new ReverseString2();
		System.out.println(ans.reverseStr(res, 2));
	}

	public String reverseStr(String s, int k) {
		char[] res = s.toCharArray();
		int len = res.length;
		for (int i = 0; i < len; i += 2 * k) {
			reverseString(res, i, Math.min(i + k, len) - 1);   //正常右边界是i+k-1,但是考虑到i+k 可能超过数组最大长度，所以需要去len和该值得最小值
		}
		return String.valueOf(res);
	}

	public void reverseString(char[] arr, int left, int right) {
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}
}
