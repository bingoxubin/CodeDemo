package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/27 12:43 下午
 */
public class SumEqualsMaxLength {
	//NC125 未排序数组中累加和为给定值的最长子数组长度  https://www.nowcoder.com/practice/704c8388a82e42e58b7f5751ec943a11?tpId=117&&tqId=37794&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：1,-2,1,1,1]  0 返回：3
	//分析：给一个数组，求在这个数组中得到和为目标值的最长子数组长度
	//思路：方式一：先求出数组的前缀和，然后用双指针，2重循环，求出结果
	//     方式二：先求出数组的前缀和，然后用hashmap缓存中间结果，求出结果值
	public static void main(String[] args) {
		int[] arr = {1, -2, 1, 1, -1};
		System.out.println(maxlenEqualK(arr, 0));
	}

	//方式一：先求出数组的前缀和，然后用双指针，2重循环，求出结果
	//   1 -2 1 1 1
	// 0 1 -1 0 1 2
	public static int maxlenEqualK(int[] arr, int k) {
		int[] helper = new int[arr.length + 1];
		for (int i = 1; i <= arr.length; i++) helper[i] = helper[i - 1] + arr[i - 1];
		System.out.println(Arrays.toString(helper));
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j + i < arr.length; j++) {
				if (helper[i + j + 1] - helper[j] == k) {
					return i + 1;
				}
			}
		}
		return 0;
	}
}
