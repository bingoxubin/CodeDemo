package com.bingoabin.algorithm.design;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/11/22 10:10 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ShuffleArray {
	//Leetcode 384. 打乱数组
	//示例：输入 ["Solution", "shuffle", "reset", "shuffle"]   [[[1, 2, 3]], [], [], []]
	//     输出 [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
	//     解释 Solution solution = new Solution([1, 2, 3]);
	//     solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
	//     solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
	//     solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
	//分析：给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。实现 Solution class:
	//     Solution(int[] nums) 使用整数数组 nums 初始化对象
	//     int[] reset() 重设数组到它的初始状态并返回
	//     int[] shuffle() 返回数组随机打乱后的结果
	//思路：重组

	int[] nums;
	int len;
	SecureRandom random = new SecureRandom();

	public ShuffleArray(int[] nums) {
		this.nums = nums;
		len = nums.length;
	}

	public int[] reset() {
		return nums;
	}

	public int[] shuffle() {
		int[] ans = nums.clone();
		for (int i = 0; i < len; i++) {
			swap(ans, i, i + random.nextInt(len - i));
		}
		return ans;
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//测试
	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		ShuffleArray shuffleArray = new ShuffleArray(nums);
		System.out.println(Arrays.toString(shuffleArray.shuffle()));
		System.out.println(Arrays.toString(shuffleArray.reset()));
		System.out.println(Arrays.toString(shuffleArray.shuffle()));
		System.out.println(Arrays.toString(shuffleArray.shuffle()));
		System.out.println(Arrays.toString(shuffleArray.reset()));
	}
}
