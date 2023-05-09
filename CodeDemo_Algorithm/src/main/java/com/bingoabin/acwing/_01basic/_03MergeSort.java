package com.bingoabin.acwing._01basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author bingoabin
 * @date 2023/5/8 23:05
 * @Description:
 */
public class _03MergeSort {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String[] strs = br.readLine().split(" ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}

		mergeSort(arr, 0, n - 1);
		for (int i = 0; i < n; i++) {
			bw.write(arr[i] + " ");
		}
		bw.flush();
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left >= right) return;
		int mid = (right - left) / 2 + left;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		mergeSort(arr, left, mid, right);
	}

	public static void mergeSort(int[] arr, int left, int mid, int right) {
		int[] res = new int[right - left + 1];
		int index = 0;
		int i = left;
		int j = mid + 1;
		while (i <= mid && j <= right) {
			res[index++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
		}
		while (i <= mid) {
			res[index++] = arr[i++];
		}
		while (j <= right) {
			res[index++] = arr[j++];
		}
		System.arraycopy(res, 0, arr, left, res.length);
	}
}
