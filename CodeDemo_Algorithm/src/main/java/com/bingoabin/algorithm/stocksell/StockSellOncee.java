package com.bingoabin.algorithm.stocksell;

/**
 * @author xubin03
 * @date 2021/5/22 2:29 下午
 */
public class StockSellOncee {
	//NC7 股票（一次交易） https://www.nowcoder.com/practice/64b4262d4e6d4f6181cd45446a5821ec?tpId=117&tqId=37717&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[1,4,2]  返回：3
	//分析：给一个数组，数组中的元素代表当天的股票价格，如果只能交易一次，求能获得的最大收益
	//思路：遍历数组，记下最小值，然后统计出后面的值，与之前最小值的差值，记下最大值
	public static void main(String[] args) {
		int[] arr = {1, 4, 2};
		System.out.println(maxProfit(arr));
	}

	//遍历数组，记下之前的最小值，然后记录，当前值与之前最小值的差值最大值
	public static int maxProfit(int[] prices) {
		int max = 0;
		int min = prices[0];
		for(int i = 1;i<prices.length;i++){
			min = Math.min(min, prices[i]);
			max = Math.max(max, prices[i] - min);
		}
		return max;
	}
}
