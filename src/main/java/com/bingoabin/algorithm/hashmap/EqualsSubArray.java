package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;

/**
 * @author bingoabin
 * @date 2021/7/8 10:56
 */
public class EqualsSubArray {
	//LeetCode 930. 和相同的二元子数组
	//示例：[0,0,0,0,0], goal = 0 返回 15
	//分析：给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。子数组 是数组的一段连续部分。
	//思路：采用hashmap方式
	public static void main(String[] args){
		int[] arr = {0, 0, 0, 0, 0};
		System.out.println(numSubarraysWithSum(arr,0));
	}

	public static int numSubarraysWithSum(int[] nums, int goal) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for(int num:nums){
			map.put(sum,map.getOrDefault(sum,0) + 1);
			sum += num;
			res += map.getOrDefault(sum - goal, 0);
		}
		return res;
	}
}
