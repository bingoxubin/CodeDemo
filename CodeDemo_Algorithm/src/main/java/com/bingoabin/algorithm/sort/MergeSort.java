package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/12 2:12
 */
public class MergeSort {
	//归并排序
	//采用递归的方式，先将原数组按照取中点的方式不断拆开，然后进行合并，合并的方式是，定义一个新数组，然后将两个数组，进行从左到右的合并，合并成一个，最终返回结果
	public static void main(String[] args) {
		int[] arr = {4, 5, 3, 2, 6, 7, 1, 8, 9, 0};
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left == right) return;
		int mid = (right + left) / 2;
 		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		mergeSort(arr, left, mid, right);
	}

	public static void mergeSort(int[] arr, int left, int mid, int right) {
		int[] helper = new int[right - left + 1];
		int index = 0;
		int i = left;
		int j = mid + 1;
		while (i <= mid && j <= right) {
			helper[index++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
		}
		while (i <= mid) {
			helper[index++] = arr[i++];
		}
		while (j <= right) {
			helper[index++] = arr[j++];
		}
		for (int k = 0; k < helper.length; k++) {
			arr[left + k] = helper[k];
		}
	}
}
