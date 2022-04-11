package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/29 21:49
 */
public class GetMidNum {
	//NC36 在两个长度相等的排序数组中找到中位数
	//样例：[1,2,3,4],[3,4,5,6] 输出 3
	//分析：给定两个有序数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。上中位数：假设递增序列长度为n，若n为奇数，则上中位数为第n/2+1个数；否则为第n个数
	//思路：方式一：通过计数的方式，从小到大
	//     方式二：通过二分查找的方式
	public static void main(String[] args) {
		int[] arr1 = {1, 2, 3, 4};
		int[] arr2 = {3, 4, 5, 6};
		System.out.println(findMedianinTwoSortedAraay1(arr1, arr2));
		System.out.println(findMedianinTwoSortedAraay2(arr1, arr2));
	}

	//方式一：通过计数的方式，从小到大
	public static int findMedianinTwoSortedAraay1(int[] arr1, int[] arr2) {
		int index1 = 0;
		int index2 = 0;
		int res = 0;
		while (index1 + index2 < arr1.length) {
			if (arr1[index1] < arr2[index2]) {
				res = arr1[index1++];
			} else {
				res = arr2[index2++];
			}
		}
		return res;
	}

	//方式二：二分arr1，可以得到对应的arr2 保证arr1的前i个和前arr2的前j个刚好凑够n个
	public static int findMedianinTwoSortedAraay2(int[] arr1, int[] arr2) {
		int N = arr1.length;
		int l = 0, r = N - 1;
		int res = 0;
		while (l <= r) {
			int mid = (l + r) >> 1;
			if (mid == N - 1)
				return Math.min(arr1[N - 1], arr2[0]);
			if (arr1[mid] == arr2[N - mid - 2])
				return arr1[mid];
			if (arr1[mid] < arr2[N - mid - 2]) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		if (r < 0)
			return Math.min(arr1[0], arr2[N - 1]);
		int a = Math.max(arr1[l], arr2[N - 2 - l]);
		int b = Math.max(arr1[r], arr2[N - 2 - r]);
		return Math.min(a, b);
	}
}
