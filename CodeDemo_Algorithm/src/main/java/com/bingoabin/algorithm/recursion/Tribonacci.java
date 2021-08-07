package com.bingoabin.algorithm.recursion;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/8/8 1:45 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Tribonacci {
	//Leetcode 1137. 第 N 个泰波那契数
	//示例：T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2；给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
	//     输入：n = 25   输出：1389537
	//分析：第0个数为0，第一个数为1，第二个数为1，第三个数为2，第4个数为4，求第n个数的泰波那契数
	//思路：方式一：迭代求解
	//     方式二: 递归优化
	public static void main(String[] args) {
		Tribonacci tribonacci = new Tribonacci();
		System.out.println(tribonacci.tribonacci(25));
		System.out.println(tribonacci.tribonacci1(25));
	}

	//方式一：迭代求解
	public int tribonacci1(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		if (n == 2) return 1;
		int res = 0;
		int left = 0;
		int mid = 1;
		int right = 1;
		for (int i = 3; i <= n; i++) {
			res = left + mid + right;
			left = mid;
			mid = right;
			right = res;
		}
		return res;
	}

	//方式二: 递归优化
	HashMap<Integer, Integer> map = new HashMap<>();

	public int tribonacci(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		if (n == 2) return 1;
		if (map.containsKey(n)) {
			return map.get(n);
		} else {
			map.put(n, tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3));
		}
		return map.get(n);
	}
}
