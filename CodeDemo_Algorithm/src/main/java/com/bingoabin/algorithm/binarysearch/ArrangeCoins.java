package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2021/10/10 1:39 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ArrangeCoins {
	//Leetcode 441. 排列硬币
	//示例：输入：n = 5  输出：2  解释：因为第三行不完整，所以返回 2 。
	//分析：你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
	//思路：方式一：减法
	//     方式二：二分法
	public static void main(String[] args) {
		ArrangeCoins arrangeCoins = new ArrangeCoins();
		System.out.println(arrangeCoins.arrangeCoins1(5));
		System.out.println(arrangeCoins.arrangeCoins2(3));
	}

	//方式一：减法
	public int arrangeCoins1(int n) {
		//定义层数
		int level = 0;
		while (n >= 0) {
			//第level + 1层 需要的个数
			level++;
			//n - level 扣掉需要的个数
			n -= level;
		}
		return level - 1;
	}

	//方式二：二分法
	public int arrangeCoins2(int n) {
		int left = 1;
		int right = n;
		while (left <= right) {
			long mid = (right - left) / 2 + left;
			long count = (1 + mid) * mid / 2;
			if (count == n) return (int) mid;
			if (count > n) {
				right = (int) mid - 1;
			} else {
				left = (int) mid + 1;
			}
		}
		return right;
	}
}
