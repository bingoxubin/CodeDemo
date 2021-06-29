package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/29 14:59
 */
public class FirstGreaterThanValue {
	//NC105 二分查找
	//样例：输入：5,4,[1,2,4,4,5]  输出：2
	//分析：输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，输出-1
	//思路：通过二分查找的方式
	public static void main(String[] args) {
		int[] arr = {1, 2, 4, 4, 5};
		System.out.println(upperBound(arr, 4));
	}

	public static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (arr[mid] == target) {
				while (mid != 0 && (arr[mid - 1] == arr[mid])) {
					mid--;
				}
				return mid;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
