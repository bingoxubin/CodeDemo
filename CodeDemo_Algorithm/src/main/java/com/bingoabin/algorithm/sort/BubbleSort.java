package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/16 1:57 上午
 */
public class BubbleSort {
	//冒泡排序
	public static void main(String[] args) {
		int[] arr = {9, 4, 6, 2, 1, 7, 8, 3, 5};
		bubbleSort1(arr);
		System.out.println(Arrays.toString(arr));
	}

	//从前往后冒泡
	public static void bubbleSort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	//从后往前冒泡
	public static void bubbleSort1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}
}
