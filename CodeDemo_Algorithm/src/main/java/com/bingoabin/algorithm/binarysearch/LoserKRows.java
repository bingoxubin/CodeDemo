package com.bingoabin.algorithm.binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xubin34
 * @date 2021/8/1 10:43 下午
 */
public class LoserKRows {
	//Leetcode 1337. 矩阵中战斗力最弱的 K 行
	//示例:输入：mat =
	// [[1,1,0,0,0],
	//  [1,1,1,1,0],
	//  [1,0,0,0,0],
	//  [1,1,0,0,0],
	//  [1,1,1,1,1]],
	// k = 3
	// 输出：[2,0,3]
	// 解释：
	// 每行中的军人数目：
	// 行 0 -> 2
	// 行 1 -> 4
	// 行 2 -> 1
	// 行 3 -> 2
	// 行 4 -> 5
	// 从最弱到最强对这些行排序后得到 [2,0,3,1,4]

	//分析：给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
	//思考：方式一：新建一个二维数组，统计下标，以及军人个数，然后进行自定义排序
	//     方式二：创建大根堆，通过二分的方式查找军人个数，然后放入大根堆中，大根堆中只放入k个元素，超过的弹出，也就是大的值弹出，最后弹出堆放到结果集最后面
	public static void main(String[] args) {
		int[][] mat = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
		System.out.println(Arrays.toString(kWeakestRows1(mat, 3)));
		System.out.println(Arrays.toString(kWeakestRows2(mat, 3)));
	}

	//方式一：新建一个二维数组，统计下标，以及军人个数，然后进行自定义排序
	public static int[] kWeakestRows1(int[][] mat, int k) {
		int row = mat.length;
		int col = mat[0].length;
		//记录下标以及军人个数
		int[][] res = new int[row][2];
		for (int i = 0; i < row; i++) {
			int count = 0;
			for (int j = 0; j < col; j++) {
				count += mat[i][j];
			}
			res[i][0] = i;
			res[i][1] = count;
		}
		//进行排序，先按照军人数顺序排序，再按照下标顺序排序
		Arrays.sort(res, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (a[1] == b[1]) {
					return a[0] - b[0];
				}
				return a[1] - b[1];
			}
		});
		//创建结果集，取前k个值
		int[] ans = new int[k];
		//返回结果
		for (int i = 0; i < k; i++) {
			ans[i] = res[i][0];
		}
		return ans;
	}

	//方式二：创建大根堆，通过二分的方式查找军人个数，然后放入大根堆中，大根堆中只放入k个元素，超过的弹出，也就是大的值弹出，最后弹出堆放到结果集最后面
	public static int[] kWeakestRows2(int[][] mat, int k) {
		//创建大根堆，也就是军人最大的放在堆顶，最小的放到堆末尾
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			//创建大根堆，数组分别表示 [数组下标,军人值]
			public int compare(int[] a, int[] b) {
				if (a[1] == b[1]) {
					return b[0] - a[0];
				}
				return b[1] - a[1];
			}
		});
		//将mat数组统计军人数，然后放入到大根堆中
		int row = mat.length;
		int col = mat[0].length;
		for (int i = 0; i < row; i++) {
			//求出军人的个数 通过二分的方式，加上最终的判断
			int left = 0, right = col - 1;
			while (left < right) {
				int mid = (right - left) / 2 + left;
				if (mat[i][mid] >= 1) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			int index = mat[i][right] >= 1 ? right + 1 : right;
			// 如果queue容量达到了k  并且queue的顶的第二项，大于index了，那么就弹出，注意当等于的时候不应该弹出，因为我想要下标小的，而弹出后，就放入了下标大的了
			if (queue.size() >= k && queue.peek()[1] > index) {
				queue.poll();
			}
			if (queue.size() < k) queue.offer(new int[]{i, index});
		}
		//求出结果集，弹出
		int[] res = new int[k];
		int inx = k - 1;
		while (!queue.isEmpty()) {
			res[inx--] = queue.poll()[0];
		}
		return res;
	}
}
