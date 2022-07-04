package com.bingoabin.algorithm.listnode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author bingoabin
 * @date 2022/7/4 18:06
 */
public class ThirdMax {
	//Leetcode 414. 第三大的数
	//示例：示例 1:
	//      输入: [3, 2, 1]
	//      输出: 1
	//      解释: 第三大的数是 1.
	//      示例 2:
	//      输入: [1, 2]
	//      输出: 2
	//      解释: 第三大的数不存在, 所以返回最大的数 2 .
	//      示例 3:
	//      输入: [2, 2, 3, 1]
	//      输出: 1
	//      解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
	//      存在两个值为2的数，它们都排第二。
	//分析：给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
	//思路：
	public static void main(String[] args) {
		ThirdMax thirdMax = new ThirdMax();
		System.out.println(thirdMax.thirdMax(new int[]{2, 2, 3, 1}));
	}

	public int thirdMax(int[] nums) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int max = Integer.MIN_VALUE;
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		for (int num : set) {
			if (num > max) max = num;
			queue.offer(num);
			if (queue.size() > 3) {
				queue.poll();
			}
		}
		return queue.size() == 3 ? queue.poll() : max;
	}
}
