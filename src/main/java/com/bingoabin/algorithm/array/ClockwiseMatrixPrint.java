package com.bingoabin.algorithm.array;

import java.util.ArrayList;

/**
 * @author xubin03
 * @date 2021/5/21 1:15 上午
 */
public class ClockwiseMatrixPrint {
	//NC38 螺旋矩阵 https://www.nowcoder.com/practice/7edf70f2d29c4b599693dc3aaeea1d31?tpId=117&tqId=37738&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[[1,2,3],[4,5,6],[7,8,9]]  返回：[1,2,3,6,9,8,7,4,5]
	//分析：给一个二维数组，按照数组，进行顺时针打印，返回结果
	//思路：
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		System.out.println(spiralOrder(arr));
	}

	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> res = new ArrayList<>();
		if (matrix.length == 0) return res;
		int left = 0;
		int right = matrix[0].length - 1;
		int up = 0;
		int down = matrix.length - 1;
		while (true) {
			for (int i = left; i <= right; i++) res.add(matrix[up][i]);
			if (++up > down) break;
			for (int i = up; i <= down; i++) res.add(matrix[i][right]);
			if (--right < left) break;
			for (int i = right; i >= left; i--) res.add(matrix[down][i]);
			if (--down < up) break;
			for (int i = down; i >= up; i--) res.add(matrix[i][left]);
			if (++left > right) break;
		}
		return res;
	}
}
