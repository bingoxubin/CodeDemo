package com.bingoabin.algorithm.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/9/3 10:11 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindKthMin {
	//Leetcode 面试题 17.14. 最小K个数
	//示例：输入： arr = [1,3,5,7,2,4,6,8], k = 4  输出： [1,2,3,4]
	//分析：给一个乱序整数数组，从中找出最小的k个数
	//思路：方式一：排序
	//     方式二：大根堆
	//     方式三：快排
	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
		System.out.println(Arrays.toString(smallestK1(arr, 4)));
		System.out.println(Arrays.toString(smallestK2(arr, 4)));
		System.out.println(Arrays.toString(smallestK3(arr, 4)));
	}

	//方式一：数组排序，然后拷贝
	public static int[] smallestK1(int[] input, int k) {
		int[] res = new int[k];
		Arrays.sort(input);
		System.arraycopy(input, 0, res, 0, k);
		return res;
	}

	//方式二：大根堆 创建一个大根堆，然后将数组数据放入大根堆中，如果大根堆长度超过k 那么就进行移出一个顶上的，最后剩下的就是最小的那几个
	public static int[] smallestK2(int[] input, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
		for (int i = 0; i < input.length; i++) {
			queue.offer(input[i]);
			if (queue.size() > k) queue.poll();
		}
		int[] res = new int[k];
		int index = k - 1;
		while (!queue.isEmpty()) {
			res[index--] = queue.poll();
		}
		return res;
	}

	//方式三：快排的方式
	public static int[] smallestK3(int[] input, int k) {
		int[] res = new int[k];
		quickSort(input, 0, input.length - 1, k);
		int index = 0;
		for (int i = 0; i < k; i++) {
			res[index++] = input[i];
		}
		return res;
	}

	public static void quickSort(int[] arr, int left, int right, int k) {
		if (left > right) {
			return;
		}
		//标记下最左边的值，作为比较的临界
		int temp = arr[left];
		//开始比较，左边的都大于temp，右边的都小于temp
		int i = left;
		int j = right;
		while (i < j) {
			while (i < j && arr[j] > temp) {
				j--;
			}
			while (i < j && arr[i] <= temp) {
				i++;
			}
			if (i < j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		//刚好到达边界，即可退出
		if (i == k - 1) {
			return;
		}
		//递归调用
		quickSort(arr, left, i - 1, k);
		quickSort(arr, i + 1, right, k);
	}
}
