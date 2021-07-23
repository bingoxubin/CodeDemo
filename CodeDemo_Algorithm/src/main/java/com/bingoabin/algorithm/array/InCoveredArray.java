package com.bingoabin.algorithm.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xubin34
 * @date 2021/7/23 1:06 上午
 */
public class InCoveredArray {
	//LeetCode 1893. 检查是否区域内所有整数都被覆盖
	//示例：输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5   输出：true
	//分析：给一个二维数组，并且给一个left right 如果left到right中的数字都被二维数组中的范围包含 那么就返回true 否则返回false
	//思路：方式一：先进行二维数组的排序，然后遍历二维数组，如果left 在二维数组范围之间，那么将left 设置为 二维数组的右边界 + 1;直到 left > right
	// 	          返回的时候，看看left 是否大于right
	//     方式二：差分数组 首先定义一个范围内的一维数组 然后遍历二维数组，左边界下标+1（表示能达到） 右边界下标后面一个 -1（表示达不到），然后进行累加到一维数组中

	//注意：1 <= ranges.length <= 50
	//     1 <= starti <= endi <= 50
	//     1 <= left <= right <= 50
	public static void main(String[] args) {
		int[][] arr = {{1, 3}, {2, 4}, {5, 6}};
		System.out.println(isCovered1(arr, 2, 5));
		System.out.println(isCovered2(arr, 2, 5));
		System.out.println(isCovered3(arr, 2, 5));
	}

	//方式一：先进行二维数组的排序，然后遍历二维数组，如果left 在二维数组范围之间，那么将left 设置为 二维数组的右边界 + 1;直到 left > right
	//       返回的时候，看看left 是否大于right
	public static boolean isCovered1(int[][] ranges, int left, int right) {
		//将二维数组排序
		Arrays.sort(ranges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		for (int[] range : ranges) {
			if (left > right) return true;
			if (range[0] <= left && left <= range[1]) {
				left = range[1] + 1;
			}
		}
		return left > right;
	}

	//方式二：差分数组 首先定义一个范围内的一维数组 然后遍历二维数组，左边界下标+1（表示能达到） 右边界下标后面一个 -1（表示达不到），然后进行累加到一维数组中
	public static boolean isCovered2(int[][] ranges, int left, int right) {
		int[] res = new int[52];
		//左边界下标值+1  右边界下标值右边一个-1 表示达不到
		for (int[] range : ranges) {
			res[range[0]]++;
			res[range[1] + 1]--;
		}
		//将数组中的值，进行累加，如果为1表示能达到，不为1表示达不到
		for (int i = 1; i < 52; i++) {
			res[i] += res[i - 1];
		}
		//判断left 到 right 的下标是否为0
		for (int i = left; i <= right; i++) {
			if (res[i] == 0) return false;
		}
		return true;
	}

	//方式二等价于下面的方式  一个意思  只是上面的方式复杂度低一些   差分数组的方式
	public static boolean isCovered3(int[][] ranges, int left, int right) {
		int[] res = new int[52];
		for (int[] range : ranges) {
			int l = range[0];
			int r = range[1];
			for (int i = l; i <= r; i++) {
				res[i]++;
			}
		}
		for (int i = left; i <= right; i++) {
			if (res[i] == 0) return false;
		}
		return true;
	}
}
