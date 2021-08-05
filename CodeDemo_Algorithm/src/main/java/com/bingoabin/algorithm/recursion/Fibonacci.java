package com.bingoabin.algorithm.recursion;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/8/6 1:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Fibonacci {
	//求Fibonacci数列，从第三项开始，每一项都是前两项之和
	// 数值：0 1 1 2 3 5 8
	// 下标：0 1 2 3 4 5 6
	public static void main(String[] args) {
		System.out.println(fib1(7));
		System.out.println(fib2(7));
		System.out.println(fib3(7));
		System.out.println(fib4(7));
		System.out.println(fib5(7));
	}

	//递归方式
	public static int fib1(int n) {
		if (n <= 1) return n;
		return fib1(n - 1) + fib1(n - 2);
	}

	//递归方式优化 hashmap方式
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static int fib2(int n) {
		if (n <= 1) return n;
		map.put(0, 0);
		map.put(1, 1);
		if (map.containsKey(n)) {
			return map.get(n);
		} else {
			map.put(n, fib2(n - 1) + fib2(n - 2));
		}
		return map.get(n);
	}

	//递归方式优化 arr数组方式
	static int[] arr = new int[40];

	public static int fib5(int n) {
		if (n <= 1) return n;
		arr[0] = 0;
		arr[1] = 1;
		if (arr[n] > 0) return arr[n];
		arr[n] = fib5(n - 1) + fib5(n - 2);
		return arr[n];
	}

	//动态规划方式
	public static int fib3(int n) {
		if (n <= 1) return n;
		int[] res = new int[n + 1];
		res[0] = 0;
		res[1] = 1;
		for (int i = 2; i <= n; i++) {
			res[i] = res[i - 1] + res[i - 2];
		}
		return res[n];
	}

	//动态规划方式优化
	public static int fib4(int n) {
		if (n <= 1) return n;
		int first = 0;
		int second = 1;
		int res = 0;
		for (int i = 2; i <= n; i++) {
			res = first + second;
			first = second;
			second = res;
		}
		return res;
	}
}
