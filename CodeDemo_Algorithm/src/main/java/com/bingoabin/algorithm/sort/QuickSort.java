package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/11 21:19
 */
public class QuickSort {
	//快排
	//先规定最左边的一个数字下标为i，然后后面的向中间靠拢，如果后面的比arr[i]小，停下，如果前面的比arr[i]大，停下，然后交换i j的值，然后交换i跟最左边的值，然后递归排序前后两部分，得出结果
	//千万注意递归退出的条件，千万千万，如果出现问题很难排查出来哦
	public static void main(String[] args) {
		int[] arr = {6, 8, 9, 2, 1, 5, 3, 6, 0, 1, 2, 4, 4, 6, 7, 9, 3, 2, 5};
		//
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void quickSort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int left, int right) {
		if (left > right) return;
		int i = left;
		int j = right;
		int temp = arr[left];
		while (i < j) {
			while (i < j && arr[j] > temp) j--;
			while (i < j && arr[i] <= temp) i++;
			if (i < j) {
				swap(arr, i, j);
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		sort(arr, left, i - 1);
		sort(arr, i + 1, right);
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//自己实现一下
	public void quickSort(int[] arr, int left, int right) {
		if (left > right) return;
		int i = left;
		int j = right;
		int temp = arr[left];
		while (i < j) {
			while (i < j && arr[j] > temp) j--;
			while (i < j && arr[i] <= temp) i++;
			if (i < j) {
				swap(arr, i, j);
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		quickSort(arr, left, i - 1);
		quickSort(arr, i + 1, right);
	}
}






























