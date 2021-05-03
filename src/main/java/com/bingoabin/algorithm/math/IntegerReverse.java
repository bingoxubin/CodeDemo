package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/3 11:38 上午
 */
public class IntegerReverse {
	public static void main(String[] args) {
		//LeetCode.7 整数反转 https://leetcode-cn.com/problems/reverse-integer/
		//案例：输入123  输出321
		//分析：将整数反转
		//思路：需要考虑临界情况，比如1534236469，本身并没有超过21亿，但是反转过来就是90亿，就越界了
		int x = 1534236469;
		System.out.println(reverse(x));
	}

	public static int reverse(int x) {
		int res = 0;
		while (x != 0) {
			//判断临界情况，需要判断前一位，如果不除以10，1534236469这种情况就不符合，或者下面的方式更严谨一些
			// if (x < Integer.MIN_VALUE / 10 || x > Integer.MAX_VALUE / 10) {
			// 	return 0;
			// }
			int temp = x % 10;
			//Integer.MAX_VALUE 2147483647
			//Integer.MIN_VALUE -2147483648
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && temp > 7)) return 0;
			if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && temp < -8)) return 0;
			res = 10 * res + temp;
			x /= 10;
		}
		return res;
	}
}
