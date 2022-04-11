package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/27 11:32 下午
 */
public class HammingDistance {
	//Leetcode 461. 汉明距离 https://leetcode-cn.com/problems/hamming-distance/
	//案例：x = 1, y = 4  输出 2
	//分析：两个数字对应二进制位不同的位置的数目。1和 4  分别为 0001  0100  共有2个位置不同
	//思路：方式一：将两个数异或，然后统计出1的个数
	//     方式二：循环遍历两个数的bit位，如果不同，相加即可
	public static void main(String[] args) {
		System.out.println(hammingDistance(1, 4));
		System.out.println(hammingDistance1(1, 4));
	}

	//方式一：将两个数异或，然后统计出1的个数
	public static int hammingDistance(int x, int y) {
		int res = x ^ y;
		int count = 0;
		while (res > 0) {
			res = res & (res - 1);
			count++;
		}
		return count;
		//return Integer.bitCount(res);
	}

	//方式二：循环遍历两个数的bit位，如果不同，相加即可
	public static int hammingDistance1(int x, int y) {
		int res = 0;
		while (x > 0 || y > 0) {
			int a = x & 1;
			int b = y & 1;
			if (a != b) {
				res++;
			}
			x >>>= 1;
			y >>>= 1;
		}
		return res;
	}
}
