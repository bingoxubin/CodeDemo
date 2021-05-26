//机器人运动范围
//地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
// 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
package com.bingoabin.newcoder;

public class _66RobotMoveRange {
	public class Solution {
		public int movingCount(int threshold, int rows, int cols) {
			int[][] result = new int[rows][cols];
			return hasMoving(0, 0, rows, cols, result, threshold);
		}

		public int hasMoving(int i, int j, int rows, int cols, int[][] result, int threshold) {
			if (i < 0 || i >= rows || j < 0 || j >= cols || getNums(i) + getNums(j) > threshold || result[i][j] == 1) {
				return 0;
			}
			result[i][j] = 1;
			return hasMoving(i - 1, j, rows, cols, result, threshold) + hasMoving(i + 1, j, rows, cols, result, threshold) + hasMoving(i, j - 1, rows, cols, result, threshold) + hasMoving(i, j + 1, rows, cols, result, threshold) + 1;
		}

		public int getNums(int i) {
			int sum = 0;
			while (i > 0) {
				sum = sum + i % 10;
				i = i / 10;
			}
			return sum;
		}
	}
}
