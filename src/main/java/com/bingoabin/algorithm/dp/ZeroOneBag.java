package com.bingoabin.algorithm.dp;

/**
 * @author bingoabin
 * @date 2021/6/16 9:39
 */
public class ZeroOneBag {
	//NC145 01背包
	//样例：10,2,[[1,3],[10,4]] 返回 4
	//分析：在体积的约束下，能得到的最大重量,前面是体积，后面是重量
	//思路：用dp数组，对于前i个产品，当背包容量为v时，可以装的最大价值是dp[i][v]
	public static void main(String[] args){
		int[][] vw = {{1,3},{10,4}};
		System.out.println(knapsack1(10,2,vw));

		int[] wt = {2, 1, 3};
		int[] val = {4, 2, 3};
		System.out.println(knapsack2(4,3,wt,val));
	}

	public static int knapsack1 (int V, int n, int[][] vw) {
		int[][] dp = new int[n+1][V+1];
		for (int i = 1; i <= n; ++i) {
			for (int v = 1; v <= V; v++) {
				if (v < vw[i-1][0]) {
					dp[i][v] = dp[i-1][v];
				} else {
					dp[i][v] = Math.max(dp[i-1][v], dp[i-1][v-vw[i-1][0]]+vw[i-1][1]);
				}
			}
		}
		return dp[n][V];
	}

	//样例：N=3 W=4 wt [2,1,3] val={4,2,3} 返回 6
	//分析：给一个可装容量W 可装N个物品的背包，每个物品有重量和价值两个属性wt[i] 价值val[i] 现在用这个背包最多能装多少价值
	//思路：用dp数组，对于前i个产品，当背包容量为w时，可以装的最大价值是dp[i][w]，在重量约束的情况下，求得最大的价值
	public static int knapsack2(int W,int N,int[] wt,int[] val){
		int[][] dp = new int[N+1][W+1];
		for(int i = 1;i<= N;i++){
			for(int w = 1;w<= W;w++){
				if(w - wt[i-1] < 0){
					dp[i][w] = dp[i-1][w];
				}else{
					dp[i][w] = Math.max( dp[i-1][w],dp[i-1][w-wt[i-1]] + val[i-1]);
				}
			}
		}
		return dp[N][W];
	}
}
