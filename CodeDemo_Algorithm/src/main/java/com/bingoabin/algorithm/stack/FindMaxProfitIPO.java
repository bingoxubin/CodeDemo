package com.bingoabin.algorithm.stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/9/8 10:19 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindMaxProfitIPO {
	//Leetcode 502 IPO
	//示例：输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
	//     输出：4
	//     解释：
	//     由于你的初始资本为 0，你仅可以从 0 号项目开始。
	//     在完成后，你将获得 1 的利润，你的总资本将变为 1。
	//     此时你可以选择开始 1 号或 2 号项目。
	//     由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
	//     因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
	//分析：即将开始IPO，为了更高的价格将股票卖给投资公司，在IPO之前开展项目增加资本。由于资源有限，只能完成k个不同项目，
	//     一个纯利润数组profits[i]  一个是需要的最小资本capital[i]，完成一个就将资本记录到自己的资本中，求最终获得的最大资本
	//     初始资本：w   最多完成不同项目个数：k   每个项目纯利润：profits[i]   每个项目最小资本：capital[i]
	//思路：
	public static void main(String[] args) {
		int[] profits = {1, 2, 3};
		int[] capital = {0, 1, 1};
		FindMaxProfitIPO findMaxProfitIPO = new FindMaxProfitIPO();
		System.out.println(findMaxProfitIPO.findMaximizedCapital(2, 0, profits, capital));
	}

	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		//先将 利润跟资本  两个数组放到二维数组中
		int n = profits.length;
		int curr = 0;
		int[][] arr = new int[n][2];

		for (int i = 0; i < n; ++i) {
			arr[i][0] = capital[i];
			arr[i][1] = profits[i];
		}
		//将二维数组进行排序
		//Arrays.sort(arr, (a, b) -> a[0] - b[0]);
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		//创建一个大根堆 将小于成本的利润值放入到queue中，如果需要的成本小于w，那么将利润放入到队列中
		PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
		for (int i = 0; i < k; ++i) {
			while (curr < n && arr[curr][0] <= w) {
				queue.add(arr[curr][1]);
				curr++;
			}
			if (!queue.isEmpty()) {
				w += queue.poll();
			} else {
				break;
			}
		}
		return w;
	}
}
