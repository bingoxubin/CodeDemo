package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2021/9/15 12:33 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindMountain {
	//Leetcode 162. 寻找峰值
	//示例：输入：nums = [1,2,1,3,5,6,4]  输出：1 或 5 解释：你的函数可以返回索引 1，其峰值元素为 2；者返回索引 5， 其峰值元素为 6。
	//分析：峰值元素是指其值严格大于左右相邻值的元素。找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。注意：你可以假设 nums[-1] = nums[n] = -∞ 。
	//思路：采用二分的方式  由于有边界条件 nums[-1] = nums[n] = -∞  所以如果mid > mid +1 那么左边一定有峰值 可以举反证  如果mid < mid + 1 那么右边一定有峰值
	public static void main(String[] args) {
		int[] arr = {1, 2, 1, 3, 5, 6, 4};
		FindMountain findMountain = new FindMountain();
		System.out.println(findMountain.findPeakElement(arr));
	}

	public int findPeakElement(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (right - left) / 2 + left;
			if (nums[mid] > nums[mid + 1]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
