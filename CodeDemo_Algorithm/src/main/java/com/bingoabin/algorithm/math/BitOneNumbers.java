package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/6/30 13:41
 */
public class BitOneNumbers {
	//Leetcode 191. 位1的个数
	//示例：示例 1：
	//      输入：00000000000000000000000000001011
	//      输出：3
	//      解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
	//      示例 2：
	//      输入：00000000000000000000000010000000
	//      输出：1
	//      解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
	//分析：编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
	//      提示：
	//      请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
	//      在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
	//      进阶：
	//      如果多次调用这个函数，你将如何优化你的算法？
	//思路：
	public static void main(String[] args) {
		BitOneNumbers bitOneNumbers = new BitOneNumbers();
		System.out.println(bitOneNumbers.hammingWeight(11));

		BitOneNumbers bitOneNumbers1 = new BitOneNumbers();
		System.out.println(bitOneNumbers1.hammingWeight1(11));
	}

	//方式一：无符号右移
	public int hammingWeight(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) == 1) {
				res++;
			}
			n >>>= 1;
		}
		return res;
	}

	//方式二：高级的方式
	public int hammingWeight1(int n) {
		int res = 0;
		while (n != 0) {
			res++;
			n = n & (n - 1);
		}
		return res;
	}
}
