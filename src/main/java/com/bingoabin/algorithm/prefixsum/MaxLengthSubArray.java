package com.bingoabin.algorithm.prefixsum;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/6/3 1:33 上午
 */
public class MaxLengthSubArray {
	//Leetcode 525. 连续数组 https://leetcode-cn.com/problems/contiguous-array/
	//样例：[0,1,0] 输出  2
	//分析：一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
	//思路：记下0 和 1的个数，碰到0，count++ 碰到1 count--,然后把中间值放到map中，如果map中有这个值，那么取出来value 做差值  就是长度
	public static void main(String[] args) {
		int[] arr = {0, 1, 0};
		System.out.println(findMaxLength(arr));
	}

	public static int findMaxLength(int[] nums) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); //表示下标为-1，因为做差值的时候 省掉了+1
		int count = 0;  //记下0 和 1的差值个数
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count++;
			} else {
				count--;
			}
			if (map.containsKey(count)) {
				res = Math.max(res, i - map.get(count));
			} else {
				map.put(count, i);
			}
		}
		return res;
	}
}
