package com.bingoabin.algorithm.design;

/**
 * @Author: xubin34
 * @Date: 2021/8/30 10:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ChooseByWeight {
	//Leetcode 528. 按权重随机选择
	//示例：输入：
	// ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
	// [[[1,3]],[],[],[],[],[]]
	// 输出：
	// [null,1,1,1,1,0]
	// 解释：
	// Solution solution = new Solution([1, 3]);
	// solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
	// solution.pickIndex(); // 返回 1
	// solution.pickIndex(); // 返回 1
	// solution.pickIndex(); // 返回 1
	// solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
	//
	// 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
	// [null,1,1,1,1,0]
	// [null,1,1,1,1,1]
	// [null,1,1,1,0,0]
	// [null,1,1,1,0,1]
	// [null,1,0,1,0,0]
	// ......
	// 诸若此类。

	//分析：给定一个正整数组，比如[1,3] 表示我获取0的概率为1/4  获取1的概率为 3/4  也就是求获取下标的概率
	//思路：前缀和 + 二分  先求出前缀和，然后算出总数  然后在这个和中取一个随机值，用二分的方式找到在该值在前缀和中的位置，也就是该值得下标
	public static void main(String[] args) {
		Solution solution = new Solution(new int[]{1, 3});

		for(int i = 0;i<100;i++){ //Math.random() 产生[0,1）的数据
			System.out.println((int)(Math.random() * 4) + 1);
		}

		for (int i = 0; i < 12; i++) {
			System.out.println(solution.pickIndex());
		}
	}
}

class Solution {
	int[] sum;

	public Solution(int[] w) {
		System.out.println((int)(Math.random() * 4));
		int n = w.length;
		sum = new int[n + 1];
		for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + w[i - 1];
	}

	public int pickIndex() {   //[0,1,4]
		int n = sum.length;
		int t = (int) (Math.random() * sum[n - 1]) + 1;
		int l = 1, r = n - 1;
		while (l < r) {
			int mid = l + r >> 1;
			if (sum[mid] >= t) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return r - 1;
	}
}
