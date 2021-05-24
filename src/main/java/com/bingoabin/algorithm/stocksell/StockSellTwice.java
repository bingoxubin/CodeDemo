package com.bingoabin.algorithm.stocksell;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 * @author xubin03
 * @date 2021/5/22 2:29 下午
 */
public class StockSellTwice {
	//NC135 股票（两次交易） https://www.nowcoder.com/practice/4892d3ff304a4880b7a89ba01f48daf9?tpId=117&tqId=37847&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[8,9,3,5,1,3]  输出：4  表示第三天买进，第四天卖出，第五天买进，第六天卖出。总收益为4。
	//分析：给出一个数组，代表当天股票的价格，如果只能够交易两次，求最大的收益
	//思路：采用动态规划的方式
	public static void main(String[] args) {
		int[] arr = {8, 9, 3, 5, 1, 3};
		System.out.println(maxProfit(arr));
	}

	public static int maxProfit(int[] prices) {
		int n = prices.length;
		if (n == 0) return 0;
		int[][] dp = new int[3][n];
		for (int i = 1; i < 3; i++) {
			int diff = -prices[0];
			for (int j = 1; j < dp[0].length; j++) {
				diff = Math.max(diff, dp[i - 1][j - 1] - prices[j]);
				dp[i][j] = Math.max(dp[i][j - 1], diff + prices[j]);
			}
		}
		System.out.println(Arrays.deepToString(dp));
		return dp[2][n - 1];
	}
}
