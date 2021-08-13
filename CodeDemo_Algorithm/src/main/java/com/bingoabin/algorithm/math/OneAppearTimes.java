package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/8/13 1:12 上午
 * @CopyRight: sankuai.com
 * @Description: 1出现的次数
 */
public class OneAppearTimes {
	//Leetcode 233. 数字 1 的个数
	//示例：输入：n = 13  输出：6
	//分析：给一个证书，计算小于等于该数的1出现的次数   比如上例 1 10 11 12 13 一共出现了6次
	//思路：
	public static void main(String[] args) {
		System.out.println(countDigitOne(15));
	}

	public static int countDigitOne(int n) {
		int res = 0;
		//高位
		int high = n;
		//低位
		int low = 0;
		//当前位
		int cur = 0;
		int num = 1;
		while (high != 0 || cur != 0) {
			cur = high % 10;
			high /= 10;
			//这里我们可以提出 high * num 因为我们发现无论为几，都含有它
			if (cur == 0) {
				res += high * num;
			} else if (cur == 1) {
				res += high * num + 1 + low;
			} else {
				res += (high + 1) * num;
			}
			//低位
			low = cur * num + low;
			num *= 10;
		}
		return res;
	}
}
