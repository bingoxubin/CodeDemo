package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/9 11:00
 * @Description:
 */
public class _05RangeOfNums {
	public static void main(String[] args) {
		//测试
		// System.out.println(getLessThanMax(new int[]{4, 5, 5, 5, 5, 5, 6, 8, 9}, 5));
		// System.out.println(getMoreThanMin(new int[]{4, 5, 5, 5, 5, 5, 6, 8, 9}, 5));

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nums = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < nums; i++) {
			int num = sc.nextInt();
			int lessThanMax = getLessThanMax(arr, num);
			int moreThanMin = getMoreThanMin(arr, num);
			if (lessThanMax == -1 || moreThanMin == -1 || arr[lessThanMax] != num || arr[moreThanMin] != num) {
				System.out.printf("%d %d", -1, -1);
				System.out.println();
			} else {
				System.out.printf("%d %d", moreThanMin, lessThanMax);
				System.out.println();
			}
		}
	}

	//小于等于目标值的最大下标
	public static int getLessThanMax(int[] arr, int num) {
		int left = 0;
		int right = arr.length - 1;
		int res = -1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (arr[mid] <= num) {
				res = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return res;
	}

	//大于等于目标值的最小下标
	public static int getMoreThanMin(int[] arr, int num) {
		int left = 0;
		int right = arr.length - 1;
		int res = -1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (arr[mid] >= num) {
				res = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return res;
	}
}
