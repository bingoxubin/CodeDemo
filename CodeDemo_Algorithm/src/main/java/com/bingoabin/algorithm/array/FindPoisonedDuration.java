package com.bingoabin.algorithm.array;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/10 1:16 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindPoisonedDuration {
	//Leetcode 495. 提莫攻击
	//示例：输入：timeSeries = [1,2], duration = 2
	//     输出：3
	//     解释：提莫攻击对艾希的影响如下：
	//     - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
	//     - 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
	//     艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
	//分析：找出中毒时间，数组表示给予的中毒时间，duration表示中毒的持续时间，中毒不叠加，求中毒时间
	//思路：
	public static void main(String[] args) {
		int[] arr = {1, 2};
		FindPoisonedDuration duration = new FindPoisonedDuration();
		out.println(duration.findPoisoneDuration(arr, 2));
		out.println(duration.findPoisoneDuration1(arr, 2));
	}

	public int findPoisoneDuration(int[] timeSeries, int duration) {
		//最终的中毒时间
		int res = 0;
		//未中毒的起始时间
		int expired = 0;
		//遍历所有的中毒起始时间
		for (int time : timeSeries) {
			if (time > expired) {
				//结果中毒时间 + 持续中毒时间
				res += duration;
			} else {
				//结果中毒时间 = 开始中毒时间 + 持续中毒时间 - 最后未中毒时间
				res += time + duration - expired;
			}
			//起始中毒时间 + 持续中毒时间 等于最后未中毒的起始时间
			expired = time + duration;
		}
		return res;
	}

	//自己实现一遍
	public int findPoisoneDuration1(int[] timeSeries, int duration) {
		int res = 0;
		int expired = 0;
		for (int time : timeSeries) {
			if (time > expired) {
				res += duration;
			} else {
				res += time + duration - expired;
			}
			expired = time + duration;
		}
		return res;
	}
}
