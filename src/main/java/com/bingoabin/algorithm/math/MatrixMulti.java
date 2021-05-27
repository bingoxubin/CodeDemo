package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/27 1:47 上午
 */
public class MatrixMulti {
	//NC143 矩阵乘法  https://www.nowcoder.com/practice/bf358c3ac73e491585943bac94e309b0?tpId=117&&tqId=37854&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//案例：输入：[[1,2],[3,2]],[[3,4],[2,1]]  返回值：[[7,6],[13,14]]
	//分析：给定两个矩阵，求出这两个矩阵相乘的结果
	//思路：用数学法进行计算
	public static void main(String[] args) {
		int[][] arr1 = {{1, 2}, {3, 2}};
		int[][] arr2 = {{3, 4}, {2, 1}};
		System.out.println(Arrays.deepToString(solve(arr1, arr2)));
	}

	public static int[][] solve(int[][] a, int[][] b) {
		int[][] res = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				res[i][j] = helper(a, b, i, j);
			}
		}
		return res;
	}

	public static int helper(int[][] a, int[][] b, int i, int j) {
		int res = 0;
		for (int k = 0; k < a[0].length; k++) {
			res += a[i][k] * b[k][j];
		}
		return res;
	}
}
