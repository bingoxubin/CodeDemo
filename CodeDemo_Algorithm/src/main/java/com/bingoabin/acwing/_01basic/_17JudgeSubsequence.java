package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/15 10:05
 * @Description:
 */
public class _17JudgeSubsequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arr1 = new int[n];
		for (int i = 0; i < n; i++) arr1[i] = sc.nextInt();
		int[] arr2 = new int[m];
		for (int i = 0; i < m; i++) arr2[i] = sc.nextInt();

		System.out.println(isSeq(arr1, arr2) ? "Yes" : "No");
	}

	public static boolean isSeq(int[] arr1, int[] arr2) {
		int i = 0;
		int j = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] == arr2[j]) i++;
			j++;
		}
		if (i == arr1.length) return true;
		return false;
	}
}
