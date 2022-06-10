package com.bingoabin.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/6/10 14:24
 */
public class QuickSort {
	public static void main(String[] args){
		int[] arr = {8, 6, 5, 4, 9, 8, 1, 2, 4, 2, 0, 4};
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void quickSort(int[] arr){
		dfs(arr, 0, arr.length - 1);
	}

	public void dfs(int[] arr,int left,int right){
		if(left > right) return;
		int temp = arr[left];
		int i = left;
		int j = right;
		while(i < j){
			while(i < j && arr[j] > temp) j--;
			while(i < j && arr[i] <= temp) i++;
			if(i < j){
				swap(arr,i,j);
			}
		}
		arr[left] = arr[i];
		arr[i] = temp;
		dfs(arr, left, i - 1);
		dfs(arr,i+1,right);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
