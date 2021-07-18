package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/16 0:22
 */
public class GetMountain {
	//Leetcode 852. 山脉数组的峰顶索引
	//样例：输入：arr = [0,10,5,2]  输出：1
	//分析：整数组成的山脉数组 arr,找到数组中的山峰下标
	//思路：方式一：遍历数组，如果数组的该值比后面的一个大  那么就返回该值，如果到数组最后一个数都找不到，那就返回最后一个值
	//     方式二：通过二分的方式
	public static void main(String[] args) {
		int[] arr = {0, 15, 5, 2};
		System.out.println(peakIndexInMountainArray1(arr));
		System.out.println(peakIndexInMountainArray2(arr));
	}

	//方式一：遍历数组，如果数组的该值比后面的一个大  那么就返回该值，如果到数组最后一个数都找不到，那就返回最后一个值
	public static int peakIndexInMountainArray1(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1 || arr[i] > arr[i + 1]) {
				return i;
			}
		}
		return -1;
	}

	//方式二：通过二分的方式
	public static int peakIndexInMountainArray2(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int mid = (right - left) / 2 + left;
			if (arr[mid] < arr[mid + 1]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
