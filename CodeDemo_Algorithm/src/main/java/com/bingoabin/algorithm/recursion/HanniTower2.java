package com.bingoabin.algorithm.recursion;

/**
 * @author bingoabin
 * @date 2023/11/14 7:32:39
 * @Description:
 */
public class HanniTower2 {
	//NC67 汉诺塔问题
	//样例：输入 2  返回：["move from left to mid","move from left to right","move from mid to right"]
	//分析：我们有从大到小放置的n个圆盘，开始时所有圆盘都放在左边的柱子上，按照汉诺塔游戏的要求我们要把所有的圆盘都移到右边的柱子上，请实现一个函数打印最优移动轨迹。
	//     给定一个int n，表示有n个圆盘。请返回一个string数组，其中的元素依次为每次移动的描述。描述格式为： move from [left/mid/right] to [left/mid/right]。
	//思路：采用递归方式
	public static void main(String[] args) {
		hanoi(3);
	}

	public static void hanoi(int n){
		dfs(n, "left", "mid", "right");
	}

	public static void dfs(int n, String sour, String mid, String des) {
		if (n < 1) return;
		dfs(n - 1, sour, des, mid);
		System.out.println("move from " + sour + " to " + des);
		dfs(n - 1, mid, sour, des);
	}
}
