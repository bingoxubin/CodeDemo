//不用加减乘除做加法
//写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。

//相加各位 + 计算进位
// 十进制思想
// 5+7 各位相加：2 进位：10
// 2+10 12 0
// 12+0
// 二进制计算过程
// 5+7 各位相加：101^111=010 进位：101&111=101 (<<1=1010)
// 2+10 各位相加：010^1010=1000 进位：010&1010=010 <<1=0100
// 8+4 1000^0100=1100 1000&0100=0
// 12+0

package com.bingoabin.newcoder;

import java.math.BigInteger;

public class _48DoPlus {

	public class Solution {
		public int Add(int num1, int num2) {
			BigInteger bi1 = new BigInteger(String.valueOf(num1));
			BigInteger bi2 = new BigInteger(String.valueOf(num2));
			return bi1.add(bi2).intValue();

		}
	}

	public class Solution1 {
		//递归版
		public int Add(int num1, int num2) {
			if (num2 == 0) {
				return num1;
			}
			return Add(num1 ^ num2, (num1 & num2) << 1);
		}
	}

	public class Solution2 {
		//非递归版
		public int Add(int num1, int num2) {
			int sum, carray;
			while (num2 != 0) {
				sum = num1 ^ num2;//异或相当于相加（但不考虑进位）
				carray = (num1 & num2) << 1;//相与并左移一位（进位）
				num1 = sum;
				num2 = carray;
			}
			return num1;
		}
	}
}
