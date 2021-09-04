package com.bingoabin.algorithm.recursion;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/9/4 1:07 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Fib {
	//leetcode 剑指 Offer 10- I. 斐波那契数列
	//示例：F(0) = 0,   F(1) = 1  F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	//分析： 0 1 1 2 3 5 8 13 21
	//      0 1 2 3 4 5 6 7   8
	//思路：方式一：动态规划方式
	//     方式二：动态规划方式优化
	//     方式三：hashmap方式优化，记录中间值
	//     方式四：数组记录中间值
	public static void main(String[] args) {
		Fib fib = new Fib();
		System.out.println(fib.fib1(8));
		System.out.println(fib.fib2(8));
		System.out.println(fib.fib3(8));
		System.out.println(fib.fib(8));
	}

	//动态规划
	public int fib1(int n) {
		int mod = 1000000007;
		int[] res = new int[n + 1];
		if (n <= 1) return n;
		res[0] = 0;
		res[1] = 1;
		for (int i = 2; i <= n; i++) {
			res[i] = res[i - 1] + res[i - 2];
			res[i] %= mod;
		}
		return res[n];
	}

	//动态规划优化
	public int fib2(int n) {
		int mod = 1000000007;
		if (n <= 1) return n;
		int left = 0;
		int right = 1;
		int res = 0;
		for (int i = 2; i <= n; i++) {
			res = left + right;
			res %= mod;
			left = right;
			right = res;
		}
		return res;
	}

	//hashmap优化
	HashMap<Integer, Integer> map = new HashMap<>();

	public int fib3(int n) {
		int mod = 1000000007;
		if (n <= 1) return n;
		map.put(0, 0);
		map.put(1, 1);
		for (int i = 2; i <= n; i++) {
			if (map.containsKey(n)) {
				return map.get(n) % mod;
			} else {
				map.put(n, (fib(n - 1) + fib(n - 2)) % mod);
			}
		}
		return map.get(n);
	}

	//数组优化
	int[] res = new int[101];

	public int fib(int n) {
		int mod = 1000000007;
		if (n <= 1) return n;
		res[0] = 0;
		res[1] = 1;
		if (res[n] != 0) return res[n] % mod;
		res[n] = (fib(n - 1) + fib(n - 2)) % mod;
		return res[n];
	}
}
