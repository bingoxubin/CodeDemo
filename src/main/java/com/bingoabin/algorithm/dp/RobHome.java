package com.bingoabin.algorithm.dp;

/**
 * @author xubin03
 * @date 2021/5/5 11:14 上午
 */
public class RobHome {
	//Leetcode.198 打家劫舍  https://leetcode-cn.com/problems/house-robber/
	//样例：[2,7,9,3,1] 返回 12
	//分析：数组中相邻的不能取，取的最大和
	//思路：用dp数组，记录最大值dp[0]=arr[0];dp[1]=Math.max(arr[0],arr[1];dp[2]=Math.max(dp[0]+dp[2],dp[1])
	public static void main(String[] args){
	    int[] arr = {2,7,9,3,1};
		System.out.println(rob(arr));
	}

	public static int rob(int[] arr){
		int len = arr.length;
		if(len == 0) {
			return 0;
		}
		if(len == 1) {
			return arr[0];
		}
		if(len == 2) {
			return Math.max(arr[0],arr[1]);
		}
		int[] dp = new int[len];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[1],arr[0]);
		for(int i = 2;i<len;i++){
			dp[i] = Math.max(arr[i]+dp[i-2],dp[i-1]);
		}
		return dp[len-1];
	}
}
