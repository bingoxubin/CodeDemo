package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/27 1:38 上午
 */
public class MaxThreeMulti {
	//NC106 三个数的最大乘积  https://www.nowcoder.com/practice/8ae05c2913fe438b8b14f3968f64fc0b?tpId=117&&tqId=37830&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//案例：[3,4,1,2] 输出 24
	//分析：给你一个数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大
	//思路：求出数组中最小的两个数  和最大的三个数   比较最小两个数和最大数的乘积   跟  三个最大数的乘积进行比较
	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2};
		System.out.println(solve(arr));
	}

	public static long solve(int[] A) {
		long min1 = Integer.MAX_VALUE;
		long min2 = Integer.MAX_VALUE;
		long max1 = Integer.MIN_VALUE;
		long max2 = Integer.MIN_VALUE;
		long max3 = Integer.MIN_VALUE;
		for (int num : A) {
			if (num < min1) {
				min2 = min1;
				min1 = num;
			} else if (num < min2) {
				min2 = num;
			}

			if (num > max1) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			} else if (num > max2) {
				max3 = max2;
				max2 = num;
			} else if (num > max3) {
				max3 = num;
			}
		}
		return Math.max(min1 * min2 * max1, max1 * max2 * max3);
	}
}
