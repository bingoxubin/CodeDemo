package com.bingoabin.algorithm.recursion;

/**
 * @Author: xubin34
 * @Date: 2021/12/5 7:56 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SuperPow {
	//Leetcode 372. 超级次方
	//示例：输入：a = 2, b = [1,0]  输出：1024
	//分析：你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
	//思路：
	public static void main(String[] args) {
		int a = 2;
		int[] arr = {1, 0};
		SuperPow superPow = new SuperPow();
		System.out.println(superPow.superPow(2, arr));
	}

	int MOD = 1337;

	public int superPow(int a, int[] b) {
		return dfs(a, b, b.length - 1);
	}

	public int dfs(int a, int[] b, int len) {
		if (len == -1) return 1;
		return pow(dfs(a, b, len - 1), 10) * pow(a, b[len]) % MOD;
	}

	public int pow(int a, int b) {
		int ans = 1;
		a %= MOD;
		while (b-- > 0) {
			ans = ans * a % MOD;
		}
		return ans;
	}
}
