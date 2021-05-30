package com.bingoabin.algorithm.recursion;

import sun.awt.SunHints;

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

	public static int[][] memo;

	public static int solve(int[][] matrix) {
		int row = matrix.length, col = matrix[0].length;
		//记录最大值
		int res = 0;
		//记录中间结果，当前点能遍历到的最大长度
		memo = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				//从该点出发能找到的最长路径值
				res = Math.max(res, dfs(matrix, i, j, Integer.MIN_VALUE));
			}
		}
		return res;
	}

	private static int dfs(int[][] matrix, int x, int y, int value) {
		if (x >= matrix.length || x < 0 || y >= matrix[0].length || y < 0) return 0;
		//value表示之前的值，x y表示要向上下左右遍历的点，如果value大 那么就无法朝这个方向遍历
		if (value >= matrix[x][y]) return 0;
		//如果中间结果，表示该点已经计算过，那么直接返回
		if (memo[x][y] != 0) return memo[x][y];
		//表示开始访问这个节点
		memo[x][y] = 1;
		//分别向上下左右遍历
		int up = dfs(matrix, x - 1, y, matrix[x][y]);
		int down = dfs(matrix, x + 1, y, matrix[x][y]);
		int right = dfs(matrix, x, y + 1, matrix[x][y]);
		int left = dfs(matrix, x, y - 1, matrix[x][y]);

		//求出当前值 跟上下左右的最大值 + 1
		memo[x][y] = Math.max(memo[x][y], Math.max(Math.max(up, down), Math.max(right, left)) + 1);
		return memo[x][y];
	}
}
