package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/30 1:01
 */
public class BinarySearchModule {

	public static void main(String[] args) {
		//左边界 情况1
		int[] arr = {1, 2, 4, 4, 4, 4, 4, 4, 6, 7, 8, 9};
		// 		     0  1  2  3  4  5  6  7  8  9  10 11
		//< 目标值 的最大值下标
		System.out.println(lessMax(arr, 5));//7
		System.out.println(lessMax(arr, 4));//1
		System.out.println(lessMax(arr, 6));//7
		System.out.println(lessMax(arr, 9));//10
		System.out.println(lessMax(arr, 10));//11
		System.out.println(lessMax(arr, 11));//11
		System.out.println(lessMax(arr, 0));//-1
		System.out.println(lessMax(arr, -1));//-1

		//左边界 情况2
		int[] arr1 = {1, 2, 4, 4, 4, 4, 4, 4, 6, 7, 8, 9};
		// 		      0  1  2  3  4  5  6  7  8  9  10 11
		//<= 目标值 的最大下标
		System.out.println(lessEqualMax(arr1, 5));//7
		System.out.println(lessEqualMax(arr1, 4));//7
		System.out.println(lessEqualMax(arr1, 6));//8
		System.out.println(lessEqualMax(arr1, 9));//11
		System.out.println(lessEqualMax(arr1, 10));//11
		System.out.println(lessEqualMax(arr1, 11));//11
		System.out.println(lessEqualMax(arr1, 1));//0
		System.out.println(lessEqualMax(arr1, 0));//-1
		System.out.println(lessEqualMax(arr1, -1));//-1

		//右边界
		int[] arr2 = {1, 2, 4, 4, 4, 4, 4, 4, 6, 7, 8, 9};
		// 		      0  1  2  3  4  5  6  7  8  9  10 11
		// > 目标值 的最小下标
		System.out.println(moreMin(arr2, 5));//8
		System.out.println(moreMin(arr2, 4));//8
		System.out.println(moreMin(arr2, 6));//9
		System.out.println(moreMin(arr2, 9));//12
		System.out.println(moreMin(arr2, 10));//12
		System.out.println(moreMin(arr2, 11));//12
		System.out.println(moreMin(arr2, 1));//1
		System.out.println(moreMin(arr2, 0));//0
		System.out.println(moreMin(arr2, -1));//0

		//右边界
		int[] arr3 = {1, 2, 4, 4, 4, 4, 4, 4, 6, 7, 8, 9};
		// 		      0  1  2  3  4  5  6  7  8  9  10 11
		// >= 目标值 的最小下标
		System.out.println(moreEqualMin(arr3, 5));//8
		System.out.println(moreEqualMin(arr3, 4));//2
		System.out.println(moreEqualMin(arr3, 6));//8
		System.out.println(moreEqualMin(arr3, 9));//11
		System.out.println(moreEqualMin(arr3, 10));//12
		System.out.println(moreEqualMin(arr3, 11));//12
		System.out.println(moreEqualMin(arr3, 0));//0
	}

	// < 目标值 的最大值下标
	public static int lessMax(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int res = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				res = mid;
				left = mid + 1;
			} else if (nums[mid] >= target) {
				right = mid - 1;
			}
		}
		return res;
	}

	// <= 目标值 的最大下标
	public static int lessEqualMax(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int res = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= target) {
				res = mid;
				left = mid + 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			}
		}
		return res;
	}

	// > 目标值 的最小下标
	public static int moreMin(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int res = nums.length;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= target) {
				left = mid + 1;
			} else if (nums[mid] > target) {
				res = mid;
				right = mid - 1;
			}
		}
		return res;
	}

	// >= 目标值 的最小下标
	public static int moreEqualMin(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int res = nums.length;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] >= target) {
				res = mid;
				right = mid - 1;
			}
		}
		return res;
	}
}
