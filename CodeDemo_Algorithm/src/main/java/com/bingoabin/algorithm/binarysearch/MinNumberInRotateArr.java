package com.bingoabin.algorithm.binarysearch;

/**
 * @author bingoabin
 * @date 2021/6/29 22:06
 */
public class MinNumberInRotateArr {
	//NC71 旋转数组的最小数字
	//样例：输入：[3,4,5,1,2]  输出：1
	//分析：输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。给出的所有元素都大于0，若数组大小为0，请返回0。
	//思路：方式一：一次遍历，当碰到i > i + 1的值，即返回i+1
	//     方式二：二分法
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 1, 2};
		System.out.println(minNumberInRotateArray1(arr));
		System.out.println(minNumberInRotateArray2(arr));
	}

	//方式一：一次遍历，当碰到i > i + 1的值，即返回i+1
	public static int minNumberInRotateArray1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return arr[i + 1];
			}
		}
		return arr[0];
	}

	//方式二：二分法  推荐
	public static int minNumberInRotateArray2(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (arr[mid] > arr[right]) {
				left = mid + 1;
			} else if (arr[mid] < arr[left]) {
				right = mid;
			} else {
				right--;
			}
		}
		return arr[left];
	}
}
