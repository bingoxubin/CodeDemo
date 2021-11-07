package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/11/7 12:02 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxCount {
	//Leetcode 598. 范围求和 II
	//示例：输入:
	//     m = 3, n = 3
	//     operations = [[2,2],[3,3]]
	//     输出: 4
	//     解释:
	//     初始状态, M =
	//     [[0, 0, 0],
	//      [0, 0, 0],
	//      [0, 0, 0]]
	//     执行完操作 [2,2] 后, M =
	//     [[1, 1, 0],
	//      [1, 1, 0],
	//      [0, 0, 0]]
	//     执行完操作 [3,3] 后, M =
	//     [[2, 2, 1],
	//      [2, 2, 1],
	//      [1, 1, 1]]
	//     M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
	//分析：给定一个m n得空矩阵，然后给出操作步骤二维矩阵，二维矩阵中的x,y，在空矩阵中横坐标 纵坐标 小于x y的每次都加上1，求最后操作完最大的数共有多少个
	//思路：方式一：通过枚举的方式
	//     方式二：通过求操作数中的最小值的方式
	public static void main(String[] args) {
		int[][] arr = {{2, 2}, {3, 3}};
		MaxCount maxCount = new MaxCount();
		System.out.println(maxCount.maxCount1(3, 3, arr));
		System.out.println(maxCount.maxCount2(3, 3, arr));
	}

	//方式一：求最小值的方式
	public int maxCount1(int m, int n, int[][] ops) {
		for (int[] op : ops) {
			m = Math.min(m, op[0]);
			n = Math.min(n, op[1]);
		}
		return m * n;
	}

	//方式二：枚举的方式  超时方式
	public int maxCount2(int m, int n, int[][] ops) {
		//新建一个空数组
		int[][] arr = new int[m][n];
		//针对没个操作数，对空数组中进行还原状态
		for (int[] op : ops) {
			for (int i = 0; i < op[0]; i++) {
				for (int j = 0; j < op[1]; j++) {
					arr[i][j]++;
				}
			}
		}
		//统计跟arr{0][0]一样的数据有多少个
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == arr[0][0]) {
					count++;
				}
			}
		}
		return count;
	}
}
