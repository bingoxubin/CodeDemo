package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/4/16 11:25
 */
public class LargestPalindrome {
	//Leetcode 479. 最大回文数乘积
	//示例：输入：n = 2
	//      输出：987
	//      解释：99 x 91 = 9009, 9009 % 1337 = 987
	//分析：给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
	//思路：
	public static void main(String[] args) {
		LargestPalindrome largestPalindrome = new LargestPalindrome();
		System.out.println(largestPalindrome.largestPalindrome(2));
	}

	public int largestPalindrome(int n) {
		if (n == 1) return 9;
		int max = (int) Math.pow(10, n) - 1;
		for (int i = max; i >= 0; i--) {
			long num = i;
			long t = i;
			while (t != 0) {
				num = num * 10 + (t % 10);
				t /= 10;
			}
			for (long j = max; j * j >= num; j--) {
				if(num % j == 0) return (int) (num % 1337);
			}
		}
		return -1;
	}
}
