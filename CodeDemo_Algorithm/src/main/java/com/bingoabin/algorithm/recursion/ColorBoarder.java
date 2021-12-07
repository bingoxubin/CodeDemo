package com.bingoabin.algorithm.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xubin34
 * @Date: 2021/12/7 9:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ColorBoarder {
	//Leetcode 1034. 边界着色
	//示例：输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3  输出：[[1,3,3],[2,3,3]]
	//     输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2     输出：[[2,2,2],[2,1,2],[2,2,2]]
	//分析：给一个二维矩阵，矩阵中的每个点表示颜色，从指定点开始出发，如果4个方向上都相同，那么用指定颜色进行着色
	//思路：
	public static void main(String[] args) {
		int[][] arr1 = {{1, 2, 2}, {2, 3, 2}};
		int[][] arr2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		ColorBoarder colorBoarder = new ColorBoarder();
		System.out.println(Arrays.deepToString(colorBoarder.colorBorder1(arr1, 0, 1, 3)));
		System.out.println(Arrays.deepToString(colorBoarder.colorBorder2(arr2, 1, 1, 2)));
	}

	//BFS
	public int[][] colorBorder1(int[][] grid, int row, int col, int color) {
		int m = grid.length, n = grid[0].length;
		int[][] ans = new int[m][n];
		int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		Deque<int[]> d = new ArrayDeque<>();
		d.addLast(new int[]{row, col});
		while (!d.isEmpty()) {
			int[] poll = d.pollFirst();
			int x = poll[0], y = poll[1], cnt = 0;
			for (int[] di : dirs) {
				int nx = x + di[0], ny = y + di[1];
				if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
				if (grid[x][y] != grid[nx][ny]) continue;
				else cnt++;
				if (ans[nx][ny] != 0) continue;
				d.addLast(new int[]{nx, ny});
			}
			ans[x][y] = cnt == 4 ? grid[x][y] : color;
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (ans[i][j] == 0) ans[i][j] = grid[i][j];
			}
		}
		return ans;
	}

	//DFS
	int m, n, c;
	int[][] grid, ans;
	int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public int[][] colorBorder2(int[][] _grid, int row, int col, int color) {
		grid = _grid;
		c = color;
		m = grid.length;
		n = grid[0].length;
		ans = new int[m][n];
		dfs(row, col);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (ans[i][j] == 0) ans[i][j] = grid[i][j];
			}
		}
		return ans;
	}

	void dfs(int x, int y) {
		int cnt = 0;
		for (int[] di : dirs) {
			int nx = x + di[0], ny = y + di[1];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
			if (grid[x][y] != grid[nx][ny]) continue;
			else cnt++;
			if (ans[nx][ny] == -1) continue;
			ans[nx][ny] = -1;
			dfs(nx, ny);
		}
		ans[x][y] = cnt == 4 ? grid[x][y] : c;
	}
}
