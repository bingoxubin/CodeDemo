package com.bingoabin.algorithm.dp;

/**
 * @author xubin03
 * @date 2021/5/5 11:37 上午
 */
public class GetMaxPoint {
	//LeetCode.740  删除并获得点数  https://leetcode-cn.com/problems/delete-and-earn/
	//样例：[2,2,3,3,3,4]  输出：9
	//分析：选择其中一个数，然后删除所有的这个数+1 或者这个数-1，比如上例中，选择了4，就必须删除3个3，因为没有5，求能选择的最大点数和
	//思路：先用数组统计出每个数出现的频次，然后类似打家劫舍，跳着选，求出最大值即可
	public static void main(String[] args){
	    int[] arr = {2,2,3,3,3,4};
		System.out.println(deleteAndEarn(arr));
	}

	public static int deleteAndEarn(int[] nums) {
		if(nums.length == 0) return 0;
		//计算频次数组的最大长度
		int max = nums[0];
		for(int num:nums) max = Math.max(max,num);
		//统计频次
		int[] times = new int[max+1];
		for(int num:nums) times[num]++;
		//调用打家劫舍的办法
		return rob(times);
	}

	public static int rob(int[] times) {
		//讨论边界情况
		int len = times.length;
		if(len == 0) return 0;
		if(len == 1) return times[0];
		if(len == 2) return Math.max(times[0],times[1]);
		//创建dp数组
		int[] dp = new int[len];
		//初始化数组
		dp[0] = times[0];
		dp[1] = Math.max(times[0],times[1]);
		//迭代计算
		for(int i = 2;i<len;i++){
			dp[i] = Math.max(i*times[i]+dp[i-2],dp[i-1]);
		}
		return dp[len-1];
	}
}
