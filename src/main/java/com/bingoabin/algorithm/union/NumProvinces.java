package com.bingoabin.algorithm.union;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/7/1 12:14
 */
public class NumProvinces {
	//LeetCode 547. 省份数量
	//样例：输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]  输出：2
	//分析：给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。返回矩阵中 省份 的数量。
	public static void main(String[] args) {
		int[][] arr = {{1, 1, 1}, {1, 1, 1}, {0, 0, 1}};
		System.out.println(findCircleNum1(arr));
		System.out.println(findCircleNum2(arr));
		System.out.println(findCircleNum3(arr));
	}

	//深度优先
	public static int findCircleNum1(int[][] isConnected) {
		int[] visited = new int[isConnected.length];
		int count = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0) {
				dfs(isConnected, visited, i);
				count++;
			}
		}
		return count;
	}

	public static void dfs(int[][] isConnected, int[] visited, int i) {
		for (int j = 0; j < isConnected.length; j++) {
			if (isConnected[i][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfs(isConnected, visited, j);
			}
		}
	}

	//广度优先
	public static int findCircleNum2(int[][] isConnected) {
		int[] visited = new int[isConnected.length];
		int ans = 0;
		Deque<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0) {
				queue.offer(i);
				while (!queue.isEmpty()) {
					int temp = queue.poll();
					visited[temp] = 1;
					for (int j = 0; j < isConnected[0].length; j++) {
						if (isConnected[temp][j] == 1 && visited[j] == 0) {
							queue.offer(j);
						}
					}
				}
				ans++;
			}
		}
		return ans;
	}

	//并查集
	public static int find(int parent[], int i) {
		if (parent[i] == -1)
			return i;
		return find(parent, parent[i]);
	}

	public static void union(int parent[], int x, int y) {
		int xset = find(parent, x);
		int yset = find(parent, y);
		if (xset != yset)
			parent[xset] = yset;
	}

	public static int findCircleNum3(int[][] M) {
		int[] parent = new int[M.length];
		Arrays.fill(parent, -1);
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				if (M[i][j] == 1 && i != j) {
					union(parent, i, j);
				}
			}
		}
		int count = 0;
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] == -1)
				count++;
		}
		return count;
	}
}
