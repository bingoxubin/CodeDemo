package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2021/9/6 8:35 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class BinarySearchTarget {
	//Leetcode
	//示例：输入: nums = [-1,0,3,5,9,12], target = 9   输出: 4  解释: 9 出现在 nums 中并且下标为 4
	//分析：在一个有序整数数组中，找到目标值，返回下标
	//思路：通过二分查找进行返回即可
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		BinarySearchTarget binarySearchTarget = new BinarySearchTarget();
		System.out.println(binarySearchTarget.search(arr, 8));
	}

	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
