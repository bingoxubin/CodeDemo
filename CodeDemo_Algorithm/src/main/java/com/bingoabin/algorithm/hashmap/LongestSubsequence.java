package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/5 10:20 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LongestSubsequence {
	//Leetcode 1218. 最长定差子序列
	//示例：输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2  输出：4    解释：最长的等差子序列是 [7,5,3,1]。
	//分析：给你一个整数数组arr和一个整数difference，请你找出并返回 arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
	//     子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
	//思路：
	public static void main(String[] args) {
		int[] arr = {1, 5, 7, 8, 5, 3, 4, 2, 1};
		LongestSubsequence longestSubsequence = new LongestSubsequence();
		out.println(longestSubsequence.longestSubsequence1(arr, -2));
		out.println(longestSubsequence.longestSubsequence2(arr, -2));
	}

	public int longestSubsequence1(int[] arr, int difference) {
		//结果集计数
		int ans = 0;
		//定义一个hashmap key放数组中的元素  放入key的时候，找一下是否已经存在key - differ 如果存在 value + 1；比如到5的时候，找一下之前是否有7，有的话value+1
		Map<Integer, Integer> dp = new HashMap<>();
		for (int v : arr) {
			dp.put(v, dp.getOrDefault(v - difference, 0) + 1);
			ans = Math.max(ans, dp.get(v));
		}
		return ans;
	}

	//自己实现一遍
	public int longestSubsequence2(int[] arr, int difference) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			map.put(num, map.getOrDefault(num - difference, 0) + 1);
			res = Math.max(res, map.get(num));
		}
		return res;
	}
}
