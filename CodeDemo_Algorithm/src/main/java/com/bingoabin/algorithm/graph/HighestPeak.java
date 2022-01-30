package com.bingoabin.algorithm.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xubin34
 * @Date: 2022/1/30 11:30 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class HighestPeak {
    //Leetcode 1765. 地图中的最高点
    //示例：输入：isWater = [[0,1],[0,0]]
    //     输出：[[1,0],[2,1]]
    //     解释：上图展示了给各个格子安排的高度。
    //     蓝色格子是水域格，绿色格子是陆地格。
    //分析：给你一个大小为m x n的整数矩阵isWater，它代表了一个由 陆地和 水域单元格组成的地图。
    //     如果isWater[i][j] == 0，格子(i, j)是一个 陆地格子。
    //     如果isWater[i][j] == 1，格子(i, j)是一个 水域格子。
    //     你需要按照如下规则给每个单元格安排高度：
    //     每个格子的高度都必须是非负的。
    //     如果一个格子是是 水域，那么它的高度必须为 0。
    //     任意相邻的格子高度差 至多为 1。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
    //     找到一种安排高度的方案，使得矩阵中的最高高度值最大。
    //     请你返回一个大小为m x n的整数矩阵 height，其中 height[i][j]是格子 (i, j)的高度。如果有多种解法，请返回任意一个。
    //思路：BFS
    public static void main(String[] args) {
        int[][] g = {{1, 0}, {0, 0}};
        HighestPeak highestPeak = new HighestPeak();
        System.out.println(Arrays.deepToString(highestPeak.highestPeak(g)));
    }

    public int[][] highestPeak(int[][] g) {
        int m = g.length, n = g[0].length;
        int[][] ans = new int[m][n];
        Deque<int[]> d = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) d.addLast(new int[]{i, j});
                ans[i][j] = g[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0], y = info[1];
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (ans[nx][ny] != -1) continue;
                ans[nx][ny] = ans[x][y] + 1;
                d.addLast(new int[]{nx, ny});
            }
        }
        return ans;
    }
}
