package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/12 11:14
 */
public class RadixSort {
	//基数排序
	//先统计出数组中最大值的位数，定义count数组0-9，helper数组用于拷贝，从个位开始计算，计算位数的值，放到count中，统计个数，然后通过helper[count[i]-1] = arr[i],进行按照个位排序
	public static void main(String[] args) {
		int[] arr = {1, 8, 7, 9, 6, 5, 3, 2, 4, 0};
		int[] arr1 = {123, 546, 39, 25, 18, 37, 1485, 24, 14, 8};
		radixSort(arr);
		radixSort(arr1);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr1));
	}

	public static void radixSort(int[] arr) {
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}

	//统计数组中最大值的位数  比如100有三位
	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int res = 0;
		while (max > 0) {
			res++;
			max /= 10;
		}
		return res;
	}

	//进行基数排序
	public static void radixSort(int[] arr, int begin, int end, int digit) {
		int[] count = new int[10];
		int[] helper = new int[end - begin + 1];
		for (int d = 1; d <= digit; d++) {
			//将位数数组进行清空，以防之前的影响后面的
			for (int i = 0; i < count.length; i++) {
				count[i] = 0;
			}
			//每个数值在当前数位上的值，放到count数组对应的下标上
			for (int i = 0; i < arr.length; i++) {
				int index = getDigit(arr[i], d);
				count[index]++;
			}
			//进行累加
			for (int i = 1; i < count.length; i++) {
				count[i] = count[i] + count[i - 1];
			}
			//根据count下标的个数  将元数组的值插入到helper中
			for (int i = arr.length - 1; i >= 0; i--) {
				int temp = getDigit(arr[i], d);
				helper[count[temp] - 1] = arr[i];
				count[temp]--;
			}
			//将helper数组中的值，拷贝到arr中
			for (int i = 0; i < arr.length; i++) {
				arr[i] = helper[i];
			}
		}
	}

	//取整数x  在 d位置上的数值
	public static int getDigit(int num, int bit) {
		return num / (int) Math.pow(10, bit - 1) % 10;
	}
}
