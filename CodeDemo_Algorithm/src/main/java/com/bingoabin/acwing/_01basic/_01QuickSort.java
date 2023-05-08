package com.bingoabin.acwing._01basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author bingoabin
 * @date 2023/5/8 22:48
 * @Description:
 */
public class _01QuickSort {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split(" ");
		int[] arrs = new int[n];
		for (int i = 0; i < n; i++) {
			arrs[i] = Integer.parseInt(strs[i]);
		}
		quickSort(arrs, 0, n - 1);
		for (int i = 0; i < n; i++) {
			bw.write(arrs[i] + " ");
		}
		bw.flush();
	}

	public static void quickSort(int[] arrs, int left, int right) {
		if (left > right) return;
		int temp = arrs[left];
		int i = left;
		int j = right;
		while (i < j) {
			while (i < j && arrs[j] > temp) j--;
			while (i < j && arrs[i] <= temp) i++;
			if (i < j) {
				int t = arrs[i];
				arrs[i] = arrs[j];
				arrs[j] = t;
			}
		}
		arrs[left] = arrs[i];
		arrs[i] = temp;
		quickSort(arrs, left, i - 1);
		quickSort(arrs, i + 1, right);
	}
}
