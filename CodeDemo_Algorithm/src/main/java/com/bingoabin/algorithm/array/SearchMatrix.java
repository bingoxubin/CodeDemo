package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/10/25 3:13 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SearchMatrix {
	//Leetcode 240. 搜索二维矩阵 II
	//示例：输入：matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, target = 5   输出：true
	// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	//分析：搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：每行的元素从左到右升序排列。每列的元素从上到下升序排列。
	//思路：从矩阵的左下角 或者右上角 开始搜索，如果比target大 往上走，如果比target小，往右走
	public static void main(String[] args) {
		int[][] arr = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
		SearchMatrix searchMatrix = new SearchMatrix();
		System.out.println(searchMatrix.searchMatrix(arr, 5));
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		//求出 举证的 行数 和列数
		int m = matrix.length;
		int n = matrix[0].length;
		//初始化 开始搜索的节点 从左下角出发
		int i = m - 1;
		int j = 0;
		while (i >= 0 && j < n) {
			if (matrix[i][j] == target) {
				return true;
			}
			//如果 当前值比target 大 那么往上走
			if (matrix[i][j] > target) {
				i--;
			} else { //否则往右走
				j++;
			}
		}
		//返回 找不到结果
		return false;
	}
}
