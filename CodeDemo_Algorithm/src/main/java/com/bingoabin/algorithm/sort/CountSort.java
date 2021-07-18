package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2021/6/12 13:29
 */
public class CountSort {
	//计数排序
	//普通版本：先计算数组中的最大值，然后定义一个数组，长度是最大值+1，然后新数组表示原数组中的数出现的次数，最后通过次数进行还原原数组进行排序
	//优化版本：计算数组中的最大值，最小值，定义数组长度为最大值最小值的差，每个数-min然后放入新数组中进行计数，这样节省空间
	public static void main(String[] args) {
		int[] arr1 = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
		countSort(arr1);
		System.out.println(Arrays.toString(arr1));

		int[] arr2 = new int[]{95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
		countSortV2(arr2);
		System.out.println(Arrays.toString(arr2));
	}

	//普通版本：先计算数组中的最大值，然后定义一个数组，长度是最大值+1，然后新数组表示原数组中的数出现的次数，最后通过次数进行还原原数组进行排序
	public static void countSort(int[] arr) {
		//求出最大值
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
		}
		//定义一个频次统计数组
		int[] res = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			res[arr[i]]++;
		}
		//将频次数组还原到arr数组中
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i]; j++) {
				arr[index++] = i;
			}
		}
	}

	//优化版本：计算数组中的最大值，最小值，定义数组长度为最大值最小值的差，每个数-min然后放入新数组中进行计数，这样节省空间
	public static void countSortV2(int[] arr) {
		//统计数组中的最大值 最小值
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
		//定义一个最大值 最小值的中间差长度的数组
		int diff = max - min;
		int[] res = new int[diff + 1];
		//将原数组中的值按照频次放入到数组中
		for (int i = 0; i < arr.length; i++) {
			res[arr[i] - min]++;
		}
		//将频次数组中的数，还原到原数组中
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i]; j++) {
				arr[index++] = i + min;
			}
		}
	}
}
