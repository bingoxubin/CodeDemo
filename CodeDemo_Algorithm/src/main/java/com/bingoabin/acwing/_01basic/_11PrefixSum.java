package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/11 19:31
 * @Description:
 */
public class _11PrefixSum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int[] res = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			res[i] = res[i - 1] + arr[i - 1];
		}

		for (int i = 0; i < m; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();
			System.out.println(res[right] - res[left - 1]);
		}
	}
}
