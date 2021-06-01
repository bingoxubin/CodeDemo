package com.bingoabin.algorithm.prefixsum;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/5/29 11:59 上午
 */
public class MatrixSumEqualsTarget {
	//LeetCode 1074. 元素和为目标值的子矩阵数量  https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/
	//样例：[[1,-1],[-1,1]] 范围 5
	//分析：给一个二维矩阵，范围元素总和等于目标值的非空子矩阵的数量。
	//思路：方式一：通过前缀和计算出当前下标的总和，再通过四重循环计算期中所有的值，外面两重表示当前矩阵右下角坐标，里面两重表示在这个下标之内的所有起点情况，如果等于目标值累加
	//     方式二：通过前缀和计算出到当前下标的总和，控制上下边界，然后不断向右进行遍历右边界，在遍历右边界的过程中，如果等于目标值，直接累加，并且记下值，后续遍历到如果存在target-已存在的 进行累加
	public static void main(String[] args) {
		int[][] arr = {{1, -1}, {-1, 1}};
		System.out.println(numSubmatrixSumTarget1(arr, 0));
		System.out.println(numSubmatrixSumTarget2(arr, 0));

	}

	//方式一：通过前缀和计算出当前下标的总和，再通过四重循环计算期中所有的值，外面两重表示当前矩阵右下角坐标，里面两重表示在这个下标之内的所有起点情况，如果等于目标值累加
	public static int numSubmatrixSumTarget1(int[][] matrix, int target) {
		int row = matrix.length, col = matrix[0].length;
		//计算前缀和
		int[][] helper = new int[row + 1][col + 1];
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				helper[i][j] = helper[i - 1][j] + helper[i][j - 1] - helper[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}
		int res = 0;
		for (int i = 1; i < helper.length; i++) {
			for (int j = 1; j < helper[i].length; j++) {
				for (int m = 1; m <= i; m++) {
					for (int n = 1; n <= j; n++) {
						if (helper[i][j] - helper[m - 1][j] - helper[i][n - 1] + helper[m - 1][n - 1] == target) {
							res++;
						}
					}
				}
			}
		}
		return res;
	}

	//方式二：通过前缀和计算出到当前下标的总和，控制上下边界，然后不断向右进行遍历右边界，在遍历右边界的过程中，如果等于目标值，直接累加，并且记下值，后续遍历到如果存在target-已存在的 进行累加
	public static int numSubmatrixSumTarget2(int[][] matrix, int target) {
		int row = matrix.length, col = matrix[0].length;
		int[][] helper = new int[row + 1][col + 1];
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				helper[i][j] = helper[i - 1][j] + helper[i][j - 1] - helper[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}
		int res = 0;
		//上边界
		for (int up = 1; up < helper.length; up++) {
			//下边界
			for (int down = up; down < helper.length; down++) {
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				//右边界
				for (int right = 1; right < helper[0].length; right++) {
					//直接判断
					int cur = helper[down][right] - helper[up - 1][right];
					if (cur == target) {
						res++;
					}
					//因为从最左边到最后边的情况，所以得记录下中间值的情况
					if (map.containsKey(cur - target)) {
						res += map.get(cur - target);
					}
					map.put(cur, map.getOrDefault(cur, 0) + 1);
				}
			}
		}
		return res;
	}
}
