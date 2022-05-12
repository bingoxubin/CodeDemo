package com.bingoabin.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/4/22 0:27
 */
public class NQueue {
	public static void main(String[] args) {
		System.out.println(1<<5);
		System.out.println(new NQueue().solveNQueens(4));
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		String[][] queue = new String[n][n];
		int[][] attack = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				attack[i][j] = 0;
				queue[i][j] = ".";
			}
		}
		backtrack(0, n, queue, attack, res);
		return res;
	}

	int max = 0;

	public void backtrack(int depth, int n, String[][] queue, int[][] attack, List<List<String>> res) {
		if (depth == n) {
			res.add(getQueueString(queue));
			return;
		}

		for (int i = 0; i < n; i++) {
			if (attack[depth][i] == 0) {
				int[][] temp = copyArray(attack);
				queue[depth][i] = "Q";
				putQueue(depth, i, attack);
				backtrack(depth + 1, n, queue, attack, res);
				attack = temp;
				queue[depth][i] = ".";
			}
		}
	}

	public void putQueue(int x, int y, int[][] attack) {
		int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
		int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

		attack[x][y] = 1;

		for (int i = 0; i < attack.length; i++) {
			for (int j = 0; j < 8; j++) {
				int nx = x + dx[j] * i;
				int ny = y + dy[j] * i;

				if (nx >= 0 && nx < attack.length && ny >= 0 && ny < attack.length) {
					attack[nx][ny] = 1;
				}
			}
		}
	}

	public List<String> getQueueString(String[][] queue) {
		List<String> res = new ArrayList<>();
		for (String[] strs : queue) {
			StringBuilder buffer = new StringBuilder();
			for (String str : strs) {
				buffer.append(str);
			}
			res.add(buffer.toString());
		}
		return res;
	}

	public int[][] copyArray(int[][] arr) {
		int[][] res = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				res[i][j] = arr[i][j];
			}
		}
		return res;
	}
}
