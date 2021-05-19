package com.bingoabin.algorithm.prefixsum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/19 2:23 下午
 */
public class KthXORDistance {
	//LeetCode.1738 找出第 K 大的异或坐标值  https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/
	//样例：[[5,2],[1,6]], k = 1  输出：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
	//分析：给一个二维数组，（1，1）相对应源数组的(0,0)(0,1)(1,0)(1,1)所有坐标值的异或和，求异或和数组对应的第k大的值
	//思路：新建一个数组，用前缀异或和的方式，求出所有的值，然后将值加入到list中，进行排序，求出最终的值
	public static void main(String[] args) {
		int[][] matrix = {{5, 2}, {1, 6}};
		System.out.println(kthLargestValue(matrix, 1));
	}

	public static int kthLargestValue(int[][] matrix, int k) {
		int row = matrix.length, col = matrix[0].length;
		int[][] res = new int[row + 1][col + 1];
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				res[i][j] = res[i - 1][j] ^ res[i][j - 1] ^ res[i - 1][j - 1] ^ matrix[i - 1][j - 1];
				result.add(res[i][j]);
			}
		}
		Collections.sort(result, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		return result.get(k - 1);
	}
}
