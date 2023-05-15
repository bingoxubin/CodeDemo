package com.bingoabin.acwing._01basic;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/12 14:29
 * @Description:
 */
public class _14BadSplitMatrix {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int q = sc.nextInt();
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int[][] res = new int[n + 1][m + 1];
		for (int i = 0; i < q; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int div = sc.nextInt();

			res[x1][y1] += div;
			if (y2 + 1 <= m) res[x1][y2 + 1] -= div;
			if (x2 + 1 <= n) res[x2 + 1][y1] -= div;
			if (x2 + 1 <= n && y2 + 1 <= m) res[x2 + 1][y2 + 1] += div;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				res[i][j] = res[i - 1][j] + res[i][j - 1] - res[i - 1][j - 1] + res[i][j];
				int ans = res[i][j] + arr[i - 1][j - 1];
				System.out.println(ans + " ");
			}
			System.out.println();
		}
	}
}
