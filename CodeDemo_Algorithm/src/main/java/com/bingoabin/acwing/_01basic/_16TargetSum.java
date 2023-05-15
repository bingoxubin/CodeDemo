package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/15 9:44
 * @Description:
 */
public class _16TargetSum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int target = sc.nextInt();

		int[] arr1 = new int[n];
		for (int i = 0; i < n; i++) arr1[i] = sc.nextInt();
		int[] arr2 = new int[m];
		for (int i = 0; i < m; i++) arr2[i] = sc.nextInt();

		int i = 0;
		int j = arr2.length - 1;
		while (i < arr1.length && j >= 0) {
			if (arr1[i] + arr2[j] > target) {
				j--;
			} else if (arr1[i] + arr2[j] < target) {
				i++;
			} else {
				System.out.printf("%d %d", i, j);
				break;
			}
		}
	}
}
