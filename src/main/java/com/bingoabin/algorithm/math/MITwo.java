package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/31 1:38 上午
 */
public class MITwo {
	//Leetcode 231. 2 的幂 https://leetcode-cn.com/problems/power-of-two/
	//案例：n = 3 返回 false
	//分析：给你一个整数 n，请你判断该整数是否是 2 的幂次方。
	//思路：方式一：判断二进制位上是否只有一位
	//     方式二：通过函数Integer.bitCount()
	public static void main(String[] args) {
		System.out.println(isPowerOfTwo1(3));
		System.out.println(isPowerOfTwo2(3));
	}

	//方式一：判断二进制位上是否只有一位
	public static boolean isPowerOfTwo1(int n) {
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;
	}

	//方式二：通过函数Integer.bitCount()
	public static boolean isPowerOfTwo2(int n) {
		if (n < 0) {
			return false;
		}
		return Integer.bitCount(n) == 1;
	}
}
