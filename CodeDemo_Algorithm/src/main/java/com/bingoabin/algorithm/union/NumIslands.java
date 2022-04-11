package com.bingoabin.algorithm.union;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/7/1 12:06
 */
public class NumIslands {
	//LeetCode 200. 岛屿数量
	//样例：输入：grid = [
	//   ["1","1","0","0","0"],
	//   ["1","1","0","0","0"],
	//   ["0","0","1","0","0"],
	//   ["0","0","0","1","1"]
	// ]
	// 输出：3
	//分析：给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
	public static void main(String[] args) {
		char[][] arr = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
		System.out.println(numIslands1(arr));
		arr = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
		System.out.println(numIslands2(arr));
		arr = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
		System.out.println(numIslands3(arr));
	}

	//方式一：Union
	public static int numIslands1(char[][] grid) {
		UnionFind uf = new UnionFind(grid);
		int nr = grid.length;
		int nc = grid[0].length;
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < nc; j++) {
				if (grid[i][j] == '1') {
					grid[i][j] = '0';
					if (i - 1 >= 0 && grid[i - 1][j] == '1') {
						uf.union(nc * i + j, (i - 1) * nc + j);
					}
					if (i + 1 < nr && grid[i + 1][j] == '1') {
						uf.union(nc * i + j, (i + 1) * nc + j);
					}
					if (j - 1 >= 0 && grid[i][j - 1] == '1') {
						uf.union(nc * i + j, i * nc + j - 1);
					}
					if (j + 1 < nc && grid[i][j + 1] == '1') {
						uf.union(nc * i + j, i * nc + j + 1);
					}
				}
			}
		}
		return uf.getCount();
	}

	//方式二：广度优先
	public static int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;

		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					++num_islands;
					grid[r][c] = '0'; // mark as visited
					Deque<Integer> queue = new LinkedList<>();
					queue.add(r * nc + c);
					while (!queue.isEmpty()) {
						int id = queue.remove();
						int row = id / nc;
						int col = id % nc;
						if (row - 1 >= 0 && grid[row - 1][col] == '1') {
							queue.add((row - 1) * nc + col);
							grid[row - 1][col] = '0';
						}
						if (row + 1 < nr && grid[row + 1][col] == '1') {
							queue.add((row + 1) * nc + col);
							grid[row + 1][col] = '0';
						}
						if (col - 1 >= 0 && grid[row][col - 1] == '1') {
							queue.add(row * nc + col - 1);
							grid[row][col - 1] = '0';
						}
						if (col + 1 < nc && grid[row][col + 1] == '1') {
							queue.add(row * nc + col + 1);
							grid[row][col + 1] = '0';
						}
					}
				}
			}
		}
		return num_islands;
	}

	//方式三：深度优先
	public static int numIslands3(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;
		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					++num_islands;
					dfs(grid, r, c);
				}
			}
		}

		return num_islands;
	}

	public static void dfs(char[][] grid, int r, int c) {
		int nr = grid.length;
		int nc = grid[0].length;

		if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
			return;
		}

		grid[r][c] = '0';
		dfs(grid, r - 1, c);
		dfs(grid, r + 1, c);
		dfs(grid, r, c - 1);
		dfs(grid, r, c + 1);
	}
}

class UnionFind {
	int count;
	int[] parent;
	int[] size;

	public UnionFind(char[][] dp) {
		int m = dp.length;
		int n = dp[0].length;
		parent = new int[m * n];
		size = new int[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				parent[i * n + j] = i * n + j;
				if (dp[i][j] == '1') count++;
				size[i * n + j] = 1;
			}
		}
	}

	public int find(int num) {
		while (parent[num] != num) {
			parent[num] = parent[parent[num]];
			num = parent[num];
		}
		return num;
	}

	public void union(int left, int right) {
		int x = find(left);
		int y = find(right);
		if (x == y) return;
		if (size[x] >= size[y]) {
			parent[y] = x;
			size[x] += size[y];
		} else {
			parent[x] = y;
			size[y] += size[x];
		}
		count--;
	}

	public int getCount() {
		return count;
	}
}
