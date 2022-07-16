package com.bingoabin.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSums {
	//Leetcode 15. 三数之和
	//分析：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
	//     注意：答案中不可以包含重复的三元组。
	//示例：
	//    给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	//    满足要求的三元组集合为：
	//    [
	//      [-1, 0, 1],
	//      [-1, -1, 2]
	//    ]
	//思路：
	public static void main(String[] args) {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		ThreeSums threeSums = new ThreeSums();
		System.out.println(threeSums.threeSum(nums));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) break;
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while (left < right && nums[left] == nums[left + 1]) left++;
					while (left < right && nums[right] == nums[right - 1]) right--;
					left++;
					right--;
				} else if (sum > 0) {
					right--;
				} else if (sum < 0) {
					left++;
				}
			}
		}
		return res;
	}
}
