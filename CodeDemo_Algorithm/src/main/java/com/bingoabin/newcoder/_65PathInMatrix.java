//矩阵中的路径
//请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
// 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
// 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
package com.bingoabin.newcoder;

public class _65PathInMatrix {
	public class Solution {
		public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
			int[] result = new int[matrix.length];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (hasPath(matrix, rows, cols, str, i, j, 0, result)) {
						return true;
					}
				}
			}
			return false;
		}

		public boolean hasPath(char[] matrix, int rows, int cols, char[] str, int x, int y, int k, int[] result) {
			int index = x * cols + y;
			if (x < 0 || x >= rows || y < 0 || y >= cols || result[index] == 1 || str[k] != matrix[index]) {
				return false;
			}
			result[index] = 1;
			if (k == str.length - 1) {
				return true;
			}
			if (hasPath(matrix, rows, cols, str, x - 1, y, k + 1, result) || hasPath(matrix, rows, cols, str, x + 1, y, k + 1, result) || hasPath(matrix, rows, cols, str, x, y - 1, k + 1, result) || hasPath(matrix, rows, cols, str, x, y + 1, k + 1, result)) {
				return true;
			}
			result[index] = 0;
			return false;
		}
	}
}
