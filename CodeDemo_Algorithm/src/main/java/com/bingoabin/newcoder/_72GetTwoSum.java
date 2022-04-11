package com.bingoabin.newcoder;

import java.util.HashMap;

/**
 * @author xumaosheng
 * 找出数组中和为S的一对组合，找出一组就行
 * @date 2019/12/19 16:48
 */
public class _72GetTwoSum {
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] a = new int[2];
		map.put(nums[0], 0);
		for (int i = 1; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				a[0] = map.get(target - nums[i]);
				a[1] = i;
				return a;
			} else {
				map.put(nums[i], i);
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] arr = {0, 1, -2, -3, 1, -1, 2};
		int[] result = twoSum(arr, 0);
		for (int temp : result) {
			System.out.print(temp + " ");
		}
	}
}
