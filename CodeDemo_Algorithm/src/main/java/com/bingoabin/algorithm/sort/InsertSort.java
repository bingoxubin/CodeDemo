package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/11 17:49
 */
public class InsertSort {
	//插入排序
	//从后面选择一个  然后不断的往前插，像抓牌一样
	public static void main(String[] args) {
		int[] arr = {4, 3, 2, 5, 6, 9, 8, 7, 1};
		insertSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j + 1] < arr[j]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
