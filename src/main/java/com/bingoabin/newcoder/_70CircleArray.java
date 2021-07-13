package com.bingoabin.newcoder;

/**
 * @author xumaosheng
 * 写一个算法，可以将一个二维数组顺时针旋转90度，说一下思路。
 * @date 2019/12/19 16:36
 */
public class _70CircleArray {
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - 1 - i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] arr = {{1, 2, 3, 4},
		               {5, 6, 7, 8},
		               {9, 10, 11, 12},
		               {13, 14, 15, 16}};
		rotate(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
