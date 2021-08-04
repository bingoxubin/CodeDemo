package com.bingoabin.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/8/5 12:54 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LastSafeState {
	//LeetCode 802. 找到最终的安全状态
	//示例：有一个数组，二维，从该点出发，随便走哪条路，经过有限次能达到终点，则该节点是安全的，求所有节点的安全点的集合
	//分析：输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]  输出：[2,4,5,6]
	//     第0个节点能到1，2   第1个节点能到2，3  第2个节点能到5  第3个节点能到0   第4个节点能到5
	//思路：
	public static void main(String[] args) {
		int[][] arr = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
		System.out.println(eventualSafeNodes(arr));
	}

	// 白色：未被访问
	// 灰色：正在被访问中
	// 黑色：访问完成，不存在环，该节点安全

	// 根据题意，若起始节点位于一个环内，或者能到达一个环，则该节点不是安全的。否则，该节点是安全的。
	// 我们可以使用深度优先搜索来找环，并在深度优先搜索时，用三种颜色对节点进行标记，标记的规则如下：
	// 白色（用 00 表示）：该节点尚未被访问；
	// 灰色（用 11 表示）：该节点位于递归栈中，或者在某个环上；
	// 黑色（用 22 表示）：该节点搜索完毕，是一个安全节点。
	// 当我们首次访问一个节点时，将其标记为灰色，并继续搜索与其相连的节点。
	// 如果在搜索过程中遇到了一个灰色节点，则说明找到了一个环，此时退出搜索，栈中的节点仍保持为灰色，这一做法可以将「找到了环」这一信息传递到栈中的所有节点上。
	// 如果搜索过程中没有遇到灰色节点，则说明没有遇到环，那么递归返回前，我们将其标记由灰色改为黑色，即表示它是一个安全的节点。
	public static List<Integer> eventualSafeNodes(int[][] graph) {
		int n = graph.length;
		int[] color = new int[n];//0白色，1灰色，2黑色
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (dfs(graph, color, i)) {
				res.add(i);
			}
		}
		return res;
	}

	public static boolean dfs(int[][] graph, int[] color, int i) {
		if (color[i] == 1) return false;
		if (color[i] == 2) return true;

		color[i] = 1;

		int[] nexts = graph[i];
		for (int next : nexts) {
			if (!dfs(graph, color, next)) {
				return false;
			}
		}
		color[i] = 2;
		return true;
	}
}
