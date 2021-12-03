package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/12/4 12:45 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LargestSumAfterKthNegations {
	//Leetcode 1005. K 次取反后最大化的数组和
	//示例：输入：nums = [3,-1,0,2], k = 3  输出：6   解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
	//分析：给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
	//     选择某个下标 i并将 nums[i] 替换为 -nums[i] 。
	//     重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
	//     以这种方式修改数组后，返回数组 可能的最大和 。
	//思路：
	public static void main(String[] args) {
		int[] arr = {3, -1, 0, 2};
		LargestSumAfterKthNegations largestSumAfterKthNegations = new LargestSumAfterKthNegations();
		System.out.println(largestSumAfterKthNegations.largestSumAfterKNegations1(arr, 3));
		System.out.println(largestSumAfterKthNegations.largestSumAfterKNegations2(arr, 3));
	}

	//方式一:K次寻找最小值
	public int largestSumAfterKNegations1(int[] A, int K) {
		//遍历循环K次
		for (int i = 0; i < K; i++) {
			//假设当前的最小值
			int min = A[0];
			//最小值的下标
			int index = 0;
			//在A中找到最小值，然后记录下标，将下标取反
			for (int j = 1; j < A.length; j++) {
				if (A[j] < min) {
					min = Math.min(min, A[j]);
					index = j;
				}
			}
			A[index] *= -1;
		}
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		return sum;
	}

	//方式二：一次排序
	public int largestSumAfterKNegations2(int[] A, int K) {
		//先进行排序
		Arrays.sort(A);
		//记录最小值的下标位置
		int minIndex = 0;
		int len = A.length;
		while (K-- > 0) {
			//最小值取反
			A[minIndex] *= -1;
			//如果下标值 小于数组最大长度  并且当前值比下一个值大，那么最小值下标右移
			if (minIndex < len - 1 && A[minIndex] > A[minIndex + 1]) {
				minIndex++;
			}
		}
		int res = 0;
		for (int a : A) {
			res += a;
		}
		return res;
	}
}
