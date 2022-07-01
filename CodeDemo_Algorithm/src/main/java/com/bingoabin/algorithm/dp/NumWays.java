package com.bingoabin.algorithm.dp;

/**
 * @author bingoabin
 * @date 2022/7/1 15:58
 */
public class NumWays {
	//Leetcode 276. 栅栏涂色
	//示例：示例:
	//      输入: n = 3，k = 2
	//      输出: 6
	//分析：有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
	//      你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个颜色相同。然后，返回所有有效涂色的方案数。
	//      注意:
	//      n 和 k 均为非负的整数。
	//思路：
	public static void main(String[] args){
		NumWays numWays = new NumWays();
		System.out.println(numWays.numWays(3,2));
	}

	public int numWays(int n, int k) {
		int[] dp = new int[n + 1];
		for(int i = 1;i<= n;i++){
			if(i == 1){
				dp[i] = k;
			}else if(i == 2){
				dp[i] = k * k;
			}else{
				dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
			}
		}
		return dp[n];
	}
}
