package com.bingoabin.algorithm.graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/7/1 10:57
 */
public class InfoPass {
	//LCP 07. 传递信息
	//样例：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
	//返回：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
	//分析：有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0；给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
	//思路：创建一个队列，先将0放入到队列中，然后先走一步，看能到哪些点，放到队列中，一共走k步，看看队列中的情况
	public static void main(String[] args) {
		int[][] arr = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
		System.out.println(numWays(5, arr, 3));
	}

	//创建一个队列，先将0放入到队列中，然后先走一步，看能到哪些点，放到队列中，一共走k步，看看队列中的情况
	public static int numWays(int n, int[][] relation, int k) {
		Deque<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		while (!queue.isEmpty() && k > 0) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int value = queue.poll();
				for (int j = 0; j < relation.length; j++) {
					if (value == relation[j][0]) {
						queue.offer(relation[j][1]);
					}
				}
			}
			k--;
		}
		if (k > 0) return 0;
		int res = 0;
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			if (temp == n - 1) res++;
		}
		return res;
	}
}
