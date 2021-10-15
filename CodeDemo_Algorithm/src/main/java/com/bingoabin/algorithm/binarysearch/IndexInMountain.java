package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2021/10/15 10:17 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IndexInMountain {
	//Leetcode 剑指 Offer II 069. 山峰数组的顶部
	//示例：输入：arr = [24,69,100,99,79,78,67,36,26,19] 输出：2
	//分析：求出山峰顶部
	//思路：
	public static void main(String[] args) {
		int[] arr = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
		IndexInMountain indexInMountain = new IndexInMountain();
		System.out.println(indexInMountain.peakIndexInMountainArray1(arr));
		System.out.println(indexInMountain.peakIndexInMountainArray2(arr));
		System.out.println(indexInMountain.peakIndexInMountainArray3(arr));
	}

	//二分
	// 根据 arr[i] > arr[i+1] 在 [0,n-2] 范围内找值
	// 峰顶元素为符合条件的最靠近中心的元素值
	public int peakIndexInMountainArray1(int[] arr) {
		int n = arr.length;
		int l = 0, r = n - 2;
		while (l < r) {
			int mid = l + r >> 1;
			if (arr[mid] > arr[mid + 1]) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return r;
	}

	//三分
	public int peakIndexInMountainArray2(int[] arr) {
		int n = arr.length;
		int l = 0, r = n - 1;
		while (l < r) {
			int m1 = l + (r - l) / 3;
			int m2 = r - (r - l) / 3;
			if (arr[m1] > arr[m2]) {
				r = m2 - 1;
			} else {
				l = m1 + 1;
			}
		}
		return r;
	}

	//k分
	public int peakIndexInMountainArray3(int[] arr) {
		int left = 0, right = arr.length - 1;
		while (left < right) {
			int m1 = left + (right - left) / 3;
			int m2 = right - (right - left + 2) / 3;
			if (arr[m1] > arr[m1 + 1]) {
				right = m1;
			} else if (arr[m2] < arr[m2 + 1]) {
				left = m2 + 1;
			} else {
				left = m1;
				right = m2;
			}
		}
		return left;
	}
}
