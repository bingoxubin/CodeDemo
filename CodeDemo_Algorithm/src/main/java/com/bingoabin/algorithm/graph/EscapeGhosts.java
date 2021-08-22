package com.bingoabin.algorithm.graph;

/**
 * @Author: xubin34
 * @Date: 2021/8/22 12:36 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class EscapeGhosts {
	//Leetcode 789. 逃脱阻碍者
	//案例：输入：ghosts = [[1,0],[0,3]], target = [0,1]
	//     输出：true
	//     解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
	//分析：开始节点从0，0出发到达target，其中有阻拦者ghosts，判断能否被阻拦者抓到
	//思路：判断距离
	public static void main(String[] args) {
		int[][] ghosts = {{1, 0}, {0, 3}};
		int[] target = {0, 1};
		EscapeGhosts escapeGhosts = new EscapeGhosts();
		System.out.println(escapeGhosts.escapeGhosts(ghosts, target));
	}

	public boolean escapeGhosts(int[][] gs, int[] target) {
		int cur = getDistince(0, 0, target[0], target[1]);
		for (int[] g : gs) {
			if (getDistince(g[0], g[1], target[0], target[1]) <= cur) return false;
		}
		return true;
	}

	public int getDistince(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
