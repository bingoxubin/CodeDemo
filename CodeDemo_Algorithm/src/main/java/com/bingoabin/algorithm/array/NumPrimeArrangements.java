package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2022/7/8 16:49
 */
public class NumPrimeArrangements {
	//Leetcode 1175. 质数排列
	//示例：示例 1：
	//      输入：n = 5
	//      输出：12
	//      解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
	//      示例 2：
	//      输入：n = 100
	//      输出：682289015
	//分析：请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
	//      让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
	//      由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
	//思路：
	public static void main(String[] args) {
		NumPrimeArrangements numPrimeArrangements = new NumPrimeArrangements();
		System.out.println(numPrimeArrangements.numPrimeArrangements(5));
	}

	public int numPrimeArrangements(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (isNumPrime(i)) {
				count++;
			}
		}
		int mod = 1000000007;
		long res = 1;
		for (int i = 1; i <= count; i++) {
			res *= i;
			res %= mod;
		}
		for (int i = 1; i <= n - count; i++) {
			res *= i;
			res %= mod;
		}
		return (int) res;
	}

	public boolean isNumPrime(int num) {
		if (num == 1) return false;
		if (num == 2) return true;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
