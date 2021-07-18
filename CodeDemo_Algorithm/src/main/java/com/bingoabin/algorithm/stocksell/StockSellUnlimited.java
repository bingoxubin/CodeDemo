package com.bingoabin.algorithm.stocksell;

/**
 * @author xubin03
 * @date 2021/5/22 2:30 下午
 */
public class StockSellUnlimited {
	//NC134 股票(无限次交易） https://www.nowcoder.com/practice/9e5e3c2603064829b0a0bbfca10594e9?tpId=117&tqId=37846&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：{1,2,3,4,5]  返回：4
	//分析：给一个数组，数组中的元素代表当天的股票价格，可以进行无限次交易，求能获得的最大收益
	//思路：用贪心算法，只要后面的值比前面的大，就进行交易，求出差值
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(maxProfit(arr));
	}

	public static int maxProfit(int[] prices) {
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				res += prices[i] - prices[i - 1];
			}
		}
		return res;
	}
}
