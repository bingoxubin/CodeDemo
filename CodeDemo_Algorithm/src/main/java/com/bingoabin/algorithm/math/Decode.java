package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/12 3:09 下午
 */
public class Decode {
	//LeetCode 1734. 解码异或后的排列 https://leetcode-cn.com/problems/decode-xored-permutation/
	//案例：输入[3,1]  输出[1,2,3]
	//分析：给你一个数组是encode后的，长度为N，它是由一个奇数个正整数1~N+1，通过arr[i] ^ arr[i+1]得来的，求原来的数组
	//思路：1.先求出1 异或到 N + 1的值
	//     2.举例原数组为1 2 3 4 5 6 7  加密后为 1^2 2^3 3^4 4^5 5^6 6^7  可以得出从下标1开始异或，间隔+2 得出2^3^4^5^6^7  跟第一步求出的结果异或，可以得出第一位
	//     3.接下去就是遍历求结果
	public static void main(String[] args) {
		int[] arr = {3, 1};
		System.out.println(Arrays.toString(decode(arr)));
	}

	public static int[] decode(int[] encoded) {
		int n = encoded.length + 1;
		int[] res = new int[n];
		//求出所有数据的异或值
		int all = 0;
		for (int i = 1; i <= n; i++) {
			all ^= i;
		}
		//求出缺少第一位的异或值
		int first = 0;
		for (int i = 1; i < encoded.length; i += 2) {
			first ^= encoded[i];
		}
		first = first ^ all;
		//求出第一个后，遍历后面的求出结果值
		res[0] = first;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] ^ encoded[i - 1];
		}
		return res;
	}
}
