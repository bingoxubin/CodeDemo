package com.bingoabin.algorithm.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author bingoabin
 * @date 2022/7/7 14:23
 */
public class MatrixReshape {
	//Leetcode 566. 重塑矩阵
	//示例：示例 1:
	//      输入:
	//      nums =
	//      [[1,2],
	//       [3,4]]
	//      r = 1, c = 4
	//      输出:
	//      [[1,2,3,4]]
	//      解释:
	//      行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
	//      示例 2:
	//      输入:
	//      nums =
	//      [[1,2],
	//       [3,4]]
	//      r = 2, c = 4
	//      输出:
	//      [[1,2],
	//       [3,4]]
	//      解释:
	//      没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
	//分析：在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
	//      给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
	//      重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
	//      如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
	//思路：
	public static void main(String[] args) {
		MatrixReshape matrixReshape = new MatrixReshape();
		int[][] nums = {{1, 2}, {3, 4}};
		System.out.println(Arrays.deepToString(matrixReshape.matrixReshape(nums, 1, 4)));
		System.out.println(Arrays.deepToString(matrixReshape.matrixReshape1(nums, 1, 4)));
	}

	//方式一：采用额外存储，队列的方式
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if (nums.length == 0 || r * c != nums.length * nums[0].length) return nums;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				queue.offer(nums[i][j]);
			}
		}
		int[][] res = new int[r][c];
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				res[i][j] = queue.poll();
			}
		}
		return res;
	}

	//方式二：不需要额外存储
	public int[][] matrixReshape1(int[][] nums, int r, int c) {
		if (nums.length == 0 || r * c != nums.length * nums[0].length) return nums;
		int[][] res = new int[r][c];
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				res[count / c][count % c] = nums[i][j];
				count++;
			}
		}
		return res;
	}
}
