package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/11 16:51
 */
public class SelectSort {
	//选择排序
	//思路：遍历一次  选择一个最小的跟最左边的交换，然后依次往后遍历
	public static void main(String[] args) {
		int[] arr = {4, 3, 2, 5, 6, 9, 8, 7, 1};
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int temp = i;
			for (int j = i+1; j < arr.length; j++) {
				temp = arr[j] < arr[temp] ? j : temp;
			}
			swap(arr, temp, i);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
