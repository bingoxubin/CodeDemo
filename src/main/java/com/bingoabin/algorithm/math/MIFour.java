package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/31 1:39 上午
 */
public class MIFour {
	//Leetcode 342. 4的幂 https://leetcode-cn.com/problems/power-of-four/
	//案例：n = 3 返回 false
	//分析：给你一个整数 n，请你判断该整数是否是 4 的幂次方。
	//思路：方式一：传统不断累除，看最后是不是1
	//     方式二：通过判断二进制位数是不是1，并且除以3  余数是1
	public static void main(String[] args) {
		System.out.println(isPowerOfFour1(16));
		System.out.println(isPowerOfFour2(16));
	}

	//方式一：传统不断累除，看最后是不是1
	public static boolean isPowerOfFour1(int n) {
		if (n <= 0) {
			return false;
		}
		while ((n % 4) == 0) {
			n /= 4;
		}
		return n == 1;
	}

	//方式二：通过判断二进制位数是不是1，并且除以3  余数是1
	public static boolean isPowerOfFour2(int n) {
		return n > 0 && ((n - 1) & n) == 0 && (n % 3) == 1;
	}
}
