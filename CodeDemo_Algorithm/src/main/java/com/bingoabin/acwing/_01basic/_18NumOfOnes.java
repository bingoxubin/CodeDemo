package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/15 10:28
 * @Description:
 */
public class _18NumOfOnes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

		int[] res = getOneNum(arr);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	public static int[] getOneNum(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = getOne(arr[i]);
		}
		return res;
	}

	public static int getOne(int num) {
		int count = 0;
		while (num != 0) {
			num = num & (num - 1);
			count++;
		}
		return count;
	}
}
