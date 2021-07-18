package com.bingoabin.newcoder;

/**
 * @author xumaosheng
 * 寻找一数组中前K个最大的数
 * @date 2019/12/19 16:59
 */
public class _74GetKthLargest {
	public static int findKthLargest(int[] nums, int k) {
		if (k < 1 || nums == null) {
			return 0;
		}

		return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
	}

	public static int getKth(int k, int[] nums, int start, int end) {

		int pivot = nums[end];

		int left = start;
		int right = end;

		while (true) {

			while (nums[left] < pivot && left < right) {
				left++;
			}

			while (nums[right] >= pivot && right > left) {
				right--;
			}

			if (left == right) {
				break;
			}

			swap(nums, left, right);
		}

		swap(nums, left, end);

		if (k == left + 1) {
			return pivot;
		} else if (k < left + 1) {
			return getKth(k, nums, start, left - 1);
		} else {
			return getKth(k, nums, left + 1, end);
		}
	}

	public static void swap(int[] nums, int n1, int n2) {
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = {9, 2, 3, 4, 5, 1, -3, 6, 7, 7};
		System.out.println(findKthLargest(arr, 3));
	}
}
