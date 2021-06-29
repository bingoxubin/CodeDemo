package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/29 15:19
 */
public class SearchInRotateArr {
	//NC48 在转动过的有序数组中寻找目标值
	//样例：输入：[6,8,10,0,2,4],10  输出：2
	//分析：在数组中搜索给出的目标值，如果能在数组中找到，返回它的索引，否则返回-1。
	//思路：通过二分查找的方式
	public static void main(String[] args) {
		int[] arr = {6, 8, 10, 0, 2, 4};
		System.out.println(search(arr, 10));
	}

	public static int search(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			//如果中间位置的值 等于目标值  直接返回
			if (arr[mid] == target) {
				return mid;
			}
			//如果左边有序  那么判断target是不是在左边范围内
			if (arr[mid] >= arr[left]) {
				if (target >= arr[left] && target < arr[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else { //如果右边有序  那么判断target是不是在右边的范围内
				if (target > arr[mid] && target <= arr[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}
}
