package com.bingoabin.algorithm.stocksell;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/22 2:29 下午
 */
public class StockSellKth {
	//LeetCode 188 股票（k次交易） https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
	//样例：k = 2, prices = [3,2,6,5,0,3]  输出：7
	//分析：给出一个数组，代表当天股票的价格，如果只能够交易次，求最大的收益
	//思路：采用动态规划的方式
	public static void main(String[] args) {
		int[] arr = {3, 2, 6, 5, 0, 3};
		int[] arr1 = {8, 9, 3, 5, 1, 3};
		System.out.println(maxProfit(2, arr1));
	}

	//    {8, 9, 3, 5, 1, 3}
	//dp: [0, 0, 0, 0, 0, 0]
	//    [0, 1, 1, 2, 2, 2]
	//    [0, 1, 1, 3, 3, 4]
	public static int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (k > n / 2) return maxProfit(prices);
		int[][] dp = new int[k + 1][n];
		for (int i = 1; i <= k; i++) {
			int temp = -prices[0];
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], temp + prices[j]);
				temp = Math.max(temp, dp[i - 1][j - 1] - prices[j]);
			}
		}
		System.out.println(Arrays.deepToString(dp));
		return dp[k][n - 1];
	}

	public static int maxProfit(int[] prices) {
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			int temp = prices[i] - prices[i - 1];
			res += temp > 0 ? temp : 0;
		}
		return res;
	}
}
