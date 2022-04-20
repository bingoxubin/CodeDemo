package com.bingoabin.algorithm.graph;

/**
 * @author bingoabin
 * @date 2022/4/20 22:46
 */
public class Dijkstra {
	public static void main(String[] args) {

	}

	public int[] getDijkstra(int[][] graph, int start) {
		int len = graph.length;//顶点数
		int[] visit = new int[len];//完成访问的点
		int[] dis = new int[len];//最短距离

		//更新开始节点到能到达节点的最短距离
		for (int i = 0; i < len; i++) {
			dis[i] = graph[start][i];
		}
		visit[start] = 1;

		for (int i = 0; i < len; i++) {
			int minDist = Integer.MAX_VALUE; //从起点到其他未被放的最短距离
			int mid = 0; //最短距离的编号

			for (int j = 0; j < len; j++) {
				if (visit[j] == 0 && dis[j] < minDist) {
					minDist = dis[j];
					mid = j;
				}
			}
			//以mid为中间节点 更新其他所有节点
			for (int j = 0; j < len; j++) {
				if (visit[j] == 0 && dis[j] > dis[mid] + graph[mid][j]) {
					dis[j] = dis[mid] + graph[mid][j];
				}
			}
			visit[mid] = 1;
		}
		return dis;
	}
}
