package com.bingoabin.algorithm.math;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 12:24 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SuperUglyNumber {
	//Leetcode 313. 超级丑数
	//示例：n = 12, primes = [2,7,13,19]  输出：32
	//     解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
	//分析：给一个数组，求出第n个超级丑数，超级丑数意思是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
	//思路：
	public static void main(String[] args) {
		int[] arr = {2, 7, 13, 19};
		System.out.println(nthSuperUglyNumber1(12, arr));
		System.out.println(nthSuperUglyNumber2(12, arr));
	}

	//方式一：用最小堆得思想，为了防止重复，采用set过滤一下
	//      首先将1放入到set和queue中，然后循环n次，就是求得最小的第n个数，第一个是1，那么将1分别乘以数组中的数放入到堆中，接下来弹出一个数，再乘以数组中的数放入到堆中，循环n次即可
	public static int nthSuperUglyNumber1(int n, int[] primes) {
		//创建hash
		Set<Long> set = new HashSet<>();
		PriorityQueue<Long> queue = new PriorityQueue<>();
		set.add(1L);
		queue.add(1L);
		int res = 0;
		for (int i = 0; i < n; i++) {
			//弹出第i小的数
			long cur = queue.poll();
			res = (int) cur;
			//将弹出的数 * 数组中的数再放入到队列中
			for (int prime : primes) {
				long next = cur * prime;
				if (set.add(next)) {
					queue.offer(next);
				}
			}
		}
		return res;
	}

	//方式二：动态规划
	public static int nthSuperUglyNumber2(int n, int[] primes) {
		//创建动态数组  最小的第n个数为dp[n-1]
		int[] dp = new int[n];
		dp[0] = 1;
		//数组长度  index数组表示相对应的primes中的数值，对应在dp中下标值
		int len = primes.length;
		int[] index = new int[len];
		for (int i = 1; i < n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < len; j++) {
				if (primes[j] * dp[index[j]] < min) {
					min = primes[j] * dp[index[j]];
				}
				dp[i] = min;
			}
			//遇到重复值，将index对应的下标值加1
			for (int j = 0; j < len; j++) {
				if (min == dp[index[j]] * primes[j]) {
					index[j]++;
				}
			}
		}
		return dp[n - 1];
	}
}
