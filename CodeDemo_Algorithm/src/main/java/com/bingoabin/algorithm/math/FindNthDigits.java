package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/11/30 10:22 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindNthDigits {
	//Leetcode 400. 第 N 位数字
	//示例：输入：n = 11  输出：0
	//     解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
	//分析：给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
	//思路：
	public static void main(String[] args) {
		FindNthDigits findNthDigits = new FindNthDigits();
		System.out.println(findNthDigits.findNthDigit(11));
		System.out.println(findNthDigits.findNthDigit(12));
	}

	public int findNthDigit(int n) {
		int len = 1;
		while (len * 9 * Math.pow(10, len - 1) < n) {
			n -= len * 9 * Math.pow(10, len - 1);
			len++;
		}
		long s = (long) Math.pow(10, len - 1);
		long x = n / len - 1 + s;
		n -= (x - s + 1) * len;
		return n == 0 ? (int) (x % 10) : (int) ((x + 1) / (int) (Math.pow(10, len - n)) % 10);
	}

	public int findNthDigit1(int n) {
		long len = 1; // 记录数字的长度[1, 2, 3, 4, 5 ...]
		long start = 1; // 不同位数的起始数字[1, 10, 100, 1000, 10000 ...]

		// 计算数字n应该从 长度为len的数字start 为起点
		while(true){
			if(n - (long) 9 * start * len <= 0) break;
			n -= 9 * start * len;
			start *= 10;
			len++;
		}

		// 从start开始计算n所在的位置
		long num = n / len;
		long mod = n % len;
		// 如果mod为0说明答案在当前数字最后一位，不为0则说明在下一个数字的mod-1位
		if(mod != 0)
			return String.valueOf(start + num).charAt( (int) mod - 1) - '0';
		return String.valueOf(start + num - 1).charAt( (int) len - 1) - '0';
	}
}
