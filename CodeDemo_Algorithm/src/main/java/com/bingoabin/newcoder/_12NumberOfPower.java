//数值的整数次方
//给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。\

//思路：
// 		懒，直接库函数
//
// 		利用数学知识：
// 		若指数为0，则无论底数如何结果都为1；
// 		若指数大于0，则直接累乘指数次。
// 		否则，先求指数的绝对值次累乘，再取倒数。
//
// 		利用位运算，回头再写。

package com.bingoabin.newcoder;

public class _12NumberOfPower {
	public class Solution {
		public double Power(double base, int exponent) {
			double result = 1.0;
			while (exponent != 0) {
				if (exponent < 0) {
					result = result * (1.0 / base);
					exponent++;
				}
				if (exponent > 0) {
					result = result * base;
					exponent--;
				}
			}
			return result;
		}
	}

	public class Solution1 {
		public double Power(double base, int exponent) {
			return Math.pow(base, exponent);
		}
	}

	public class Solution2 {
		public double Power(double base, int exponent) {
			double result = 1d;
			int i;
			if (exponent == 0) {
				return 1d;
			} else if (exponent > 0) {
				for (i = 0; i < exponent; i++) {
					result *= base;
				}
			} else {
				exponent = -exponent;
				for (i = 0; i < exponent; i++) {
					result *= base;
				}
				result = 1 / result;
			}
			return result;
		}
	}
}
