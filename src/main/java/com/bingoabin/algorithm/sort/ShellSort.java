package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/12 2:09
 */
public class ShellSort {
	//希尔排序
	//类似于插入排序，但是是跳着一定间隔的插入排序，一开始假设间隔gap为len/2,然后最外层gap每次减半，然后从gap位置往后遍历，定义为i，如果j>= gap 而且arr[j-gap] > arr[j] 交换 并且j = j-gap;
	public static void main(String[] args) {
		int[] arr = {1, 8, 7, 9, 6, 5, 3, 2, 4, 0};
		shellSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void shellSort(int[] arr) {
		int gap = arr.length / 2;
		while (gap > 0) {
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				while (j >= gap && arr[j - gap] > arr[j]) {
					swap(arr, j - gap, j);
					j = j - gap;
				}
			}
			gap /= 2;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
