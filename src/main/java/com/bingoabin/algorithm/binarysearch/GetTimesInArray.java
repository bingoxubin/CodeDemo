package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/29 23:15
 */
public class GetTimesInArray {
	//NC74 数字在升序数组中出现的次数
	//样例：输入：[1,2,3,3,3,3,4,5],3 输出：4
	//分析：统计一个数字在升序数组中出现的次数。
	//思路：方式一：二分法
	//     方式二：二分法
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
		System.out.println(GetNumberOfK1(arr, 3));
		System.out.println(GetNumberOfK2(arr, 3));
	}

	//方式一：通过 -1 的方式
	public static int GetNumberOfK1(int[] nums, int K) {
		return lessMax(nums, K + 1) - lessMax(nums, K);
	}

	// < 目标值 的最大值下标
	public static int lessMax(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] >= target) {
				right = mid - 1;
			}
		}
		return right;
	}

	//方式二：通过 double的方式
	public static int GetNumberOfK2(int[] nums, int K) {
		return lessMaxDouble(nums, K + 0.5) - lessMaxDouble(nums, K - 0.5);
	}

	// < 目标值 的最大值下标
	public static int lessMaxDouble(int[] nums, double target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] >= target) {
				right = mid - 1;
			}
		}
		return right;
	}
}
