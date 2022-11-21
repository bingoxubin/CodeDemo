package com.bingoabin.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/11/13 21:17
 * @Description:
 */
public class Test3 {
	public static void main(String[] args){
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// int m = sc.nextInt();
		// int q = sc.nextInt();
		// int[][] arr = new int[n][m];
		// for(int i = 0;i<n;i++){
		// 	for(int j = 0;j<m;j++){
		// 		arr[i][j] = sc.nextInt();
		// 	}
		// }

		int n = 3;
		int m = 4;
		int q = 3;
		int[][] arr = {{1, 2, 2, 1}, {3, 2, 2, 1}, {1, 1, 1, 1}};
		int[][] res = new int[n + 1][m + 1];

		int[] a = {1, 1, 3};
		int[] b = {1, 3, 1};
		int[] c = {2, 2, 3};
		int[] d = {2, 3, 4};
		int[] e = {1, 2, 1};
		for(int i = 0;i<1;i++){
			int x1 = a[i];
			int y1 = b[i];
			int x2 = c[i];
			int y2 = d[i];
			int div = e[i];

			res[x1][y1] += div;
			if(y2 + 1 <= m)res[x1][y2+1] -= div;
			if(x2 + 1 <= n)res[x2+1][y1] -= div;
			if(x2 + 1 <= n && y2 + 1 <= m) res[x2 + 1][y2 + 1] += div;
		}

		System.out.println(Arrays.deepToString(res));

		for(int i = 1;i<= n;i++){
			for(int j = 1;j<= m;j++){
				res[i][j] = res[i-1][j] + res[i][j-1] - res[i-1][j-1] + res[i][j];
			}
		}
		System.out.println(Arrays.deepToString(res));
		//int result = res[i][j] + arr[i-1][j-1];
		// 				System.out.print(result + " ");
	}
}
