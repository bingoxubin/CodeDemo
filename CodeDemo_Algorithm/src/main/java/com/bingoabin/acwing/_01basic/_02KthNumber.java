package com.bingoabin.acwing._01basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author bingoabin
 * @date 2023/5/8 22:55
 * @Description:
 */
public class _02KthNumber {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] strs = br.readLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		int k = Integer.parseInt(strs[1]);

		String[] nums = br.readLine().split(" ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(nums[i]);
		}

		quickSort(arr, 0, n - 1, k - 1);
		bw.write(arr[k - 1] + "");
		bw.flush();
	}

	public static void quickSort(int[] arr, int left, int right, int k) {
		if (left > right) return;
		int temp = arr[left];
		int i = left;
		int j = right;
		while (i < j) {
			while (i < j && arr[j] > temp) j--;
			while (i < j && arr[i] <= temp) i++;
			if (i < j) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		if (i == k) return;
		quickSort(arr, left, i - 1, k);
		quickSort(arr, i + 1, right, k);
	}
}
