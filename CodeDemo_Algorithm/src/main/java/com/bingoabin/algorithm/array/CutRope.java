package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2022/7/21 10:03
 */
public class CutRope {
	//Leetcode 剑指 Offer 14- I. 剪绳子
	//示例：示例 1：
	//      输入: 2
	//      输出: 1
	//      解释: 2 = 1 + 1, 1 × 1 = 1
	//      示例 2:
	//      输入: 10
	//      输出: 36
	//      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
	//分析：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
	//      每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
	//      例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	//思路：
	public static void main(String[] args) {
		CutRope cutRope = new CutRope();
		System.out.println(cutRope.cutRope(10));
		System.out.println(cutRope.cutRope1(10));
	}

	//贪心算法  先求尽可能多的分成多少个3  如果最后剩下1  那么就分成2个2
	public int cutRope(int n) {
		if (n <= 1) return 0;
		if (n == 2) return 1;
		if (n == 3) return 2;
		int three = n / 3;
		if (n - three * 3 == 1) {
			three -= 1;
		}
		int two = (n - three * 3) / 2;
		return (int) (Math.pow(3.0, three) * Math.pow(2.0, two));
	}

	//动态规划
	public int cutRope1(int n) {
		if (n <= 1) return 0;
		if (n == 2) return 1;
		if (n == 3) return 2;
		int[] res = new int[n + 1];
		res[0] = 0;
		res[1] = 1;
		res[2] = 2;
		res[3] = 3;
		for (int i = 4; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= i / 2; j++) {
				int temp = res[j] * res[i - j];
				if (temp > sum) {
					sum = temp;
				}
			}
			res[i] = sum;
		}
		return res[n];
	}
}
