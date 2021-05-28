package com.bingoabin.algorithm.recursion;

/**
 * @author xubin03
 * @date 2021/5/28 1:35 上午
 */
public class MatrixMaxLengthDis {
	//NC138 矩阵最长递增路径  https://www.nowcoder.com/practice/7a71a88cdf294ce6bdf54c899be967a2?tpId=117&&tqId=37850&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：[[1,2,3],[4,5,6],[7,8,9]]  返回 5  比如1->2->3->6->9即可。当然这种递增路径不是唯一的。
	//分析：给一个二维数组，找出数组中最长的递增路径，情况可能多种
	//思路：采用记忆化搜索 深度遍历
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		System.out.println(solve(arr));
	}

	public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static int rows, columns;

	public static int solve(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		rows = matrix.length;
		columns = matrix[0].length;
		int[][] memo = new int[rows][columns];
		int ans = 0;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				ans = Math.max(ans, dfs(matrix, i, j, memo));
			}
		}
		return ans;
	}

	public static int dfs(int[][] matrix, int row, int column, int[][] memo) {
		if (memo[row][column] != 0) {
			return memo[row][column];
		}
		++memo[row][column];
		for (int[] dir : dirs) {
			int newRow = row + dir[0], newColumn = column + dir[1];
			if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
				memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
			}
		}
		return memo[row][column];
	}
}
