package com.bingoabin.algorithm.binarysearch;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/4 12:46 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IsPerfectSquare {
	//Leetcode 367. 有效的完全平方数
	//示例：输入：num = 16 输出：true    输入：num = 14  输出：false
	//分析：给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。进阶：不要 使用任何内置的库函数，如sqrt 。
	//思路：通过二分法来进行判断
	public static void main(String[] args) {
		IsPerfectSquare isPerfectSquare = new IsPerfectSquare();
		out.println(isPerfectSquare.isPerfectSquare(14));
		out.println(isPerfectSquare.isPerfectSquare(16));
	}

	public boolean isPerfectSquare(int num) {
		//先判断是1的情况，返回true
		if (num == 1) {
			return true;
		}
		//定义左边界2
		long left = 2;
		//定义右边界num/2，注意用long来表示，用int的话，奇偶都无法算
		long right = num / 2;
		while (left <= right) {
			//取得中间值
			long mid = (left + right) / 2;
			//如果是平方数 返回
			if (mid * mid == num) {
				return true;
				//如果平方数 小于目标值 那么左边扩大
			} else if (mid * mid < num) {
				left = mid + 1;
				//如果平方数 大于目标值 那么右边减少
			} else {
				right = mid - 1;
			}
		}
		return false;
	}
}
