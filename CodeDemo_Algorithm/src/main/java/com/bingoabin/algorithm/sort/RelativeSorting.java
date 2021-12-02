package com.bingoabin.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/12/2 10:21 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RelativeSorting {
	//Leetcode 506. 相对名次
	//示例：输入：score = [10,3,8,9,4]
	//     输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
	//     解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
	//分析：给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
	//     运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
	//     名次第 1 的运动员获金牌 "Gold Medal" 。
	//     名次第 2 的运动员获银牌 "Silver Medal" 。
	//     名次第 3 的运动员获铜牌 "Bronze Medal" 。
	//     从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
	//     使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
	//思路：
	public static void main(String[] args) {
		int[] score = {10, 3, 8, 9, 4};
		RelativeSorting relativeSorting = new RelativeSorting();
		System.out.println(Arrays.toString(relativeSorting.findRelativeRanks(score)));
	}

	public String[] findRelativeRanks(int[] score) {
		//记录好原始的顺序位置 在map中
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < score.length; i++) {
			map.put(score[i], i);
		}
		//将数组进行排序
		Arrays.sort(score);
		//创建结果集
		String[] res = new String[score.length];
		//从排序后的数组，最后一个位置开始循环遍历，如果是最后一个，那么当前id就是第一，类推
		for (int i = score.length - 1; i >= 0; i--) {
			if (i == score.length - 1) {
				res[map.get(score[i])] = "Gold Medal";
			} else if (i == score.length - 2) {
				res[map.get(score[i])] = "Silver Medal";
			} else if (i == score.length - 3) {
				res[map.get(score[i])] = "Bronze Medal";
			} else {
				res[map.get(score[i])] = String.valueOf(score.length - i);
			}
		}
		return res;
	}
}
