package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/8/4 10:58 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TriangleNumbers {
	//Leetcode 611 有效三角形的个数
	//示例：输入[2,2,3,4]  输出：3  有效组合 （2，2，3） （2，2，3） （2，3，4）
	//分析：给一个无序数组，统计出可以组成三角形的三元组个数
	//思路：先对数组进行排序，然后第一个数从0下标开始，然后从1下标开始，下面定义k  先跟j相同，然后k+1 是否小于两数之和，如果是的话k++,此时k表示满足条件的值，进行累加 k-j
	public static void main(String[] args) {
		int[] arr = {2, 2, 3, 4};
		System.out.println(triangleNumber(arr));
	}

	public static int triangleNumber(int[] nums) {
		int len = nums.length;
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int k = j;
				while (k < len - 1 && nums[k + 1] < nums[i] + nums[j]) {
					k++;
				}
				res += Math.max(0, k - j);
			}
		}
		return res;
	}
}
