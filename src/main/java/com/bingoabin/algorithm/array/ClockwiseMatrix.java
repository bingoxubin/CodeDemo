package com.bingoabin.algorithm.array;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/21 2:04 上午
 */
public class ClockwiseMatrix {
	//NC18顺时针旋转矩阵 https://www.nowcoder.com/practice/2e95333fbdd4451395066957e24909cc?tpId=117&&tqId=37790&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：[[[1,2,3],[4,5,6],[7,8,9]],3  返回：[[7,4,1],[8,5,2],[9,6,3]]
	//分析：给一个二维数组，按照数组，进行顺时针旋转打印新的矩阵
	//思路：方式一：进行推断，原来的矩阵-》目标的矩阵  横坐标变为之后的纵坐标，纵坐标变为后来的n-1-i，需要新开辟数组，占用空间
	//     方式二：在原数组上进行更改，先上下翻转，然后再主对角线翻转,不需要新开辟空间，节省空间复杂度
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] res = rotateMatrix(arr, 3);
		for (int[] value : res) {
			System.out.println(Arrays.toString(value));
		}
	}

	//方式一：进行推断，原来的矩阵-》目标的矩阵  横坐标变为之后的纵坐标，纵坐标变为后来的n-1-i，需要新开辟数组，占用空间
	public static int[][] rotateMatrix(int[][] mat, int n) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res[j][n - 1 - i] = mat[i][j];
			}
		}
		return res;
	}

	//方式二：在原数组上进行更改，先上下翻转，然后再主对角线翻转
	public static int[][] rotateMatrix1(int[][] mat, int n) {
		//上下翻转
		for (int i = 0; i < n / 2; i++) {
			int[] tmp = mat[i];
			mat[i] = mat[n - i - 1];
			mat[n - i - 1] = tmp;
		}
		//主对角线翻转
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				int tmp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = tmp;
			}
		}
		return mat;
	}
}
