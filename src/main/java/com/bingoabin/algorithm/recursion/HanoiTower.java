package com.bingoabin.algorithm.recursion;

import java.util.ArrayList;

/**
 * @author bingoabin
 * @date 2021/6/30 1:45
 */
public class HanoiTower {
	//NC67 汉诺塔问题
	//样例：输入 2  返回：["move from left to mid","move from left to right","move from mid to right"]
	//分析：我们有从大到小放置的n个圆盘，开始时所有圆盘都放在左边的柱子上，按照汉诺塔游戏的要求我们要把所有的圆盘都移到右边的柱子上，请实现一个函数打印最优移动轨迹。
	//     给定一个int n，表示有n个圆盘。请返回一个string数组，其中的元素依次为每次移动的描述。描述格式为： move from [left/mid/right] to [left/mid/right]。
	//思路：采用递归方式
	public static void main(String[] args) {
		System.out.println(getSolution(12));
		System.out.println(res.size());
	}

	static ArrayList<String> res = new ArrayList<String>();

	public static ArrayList<String> getSolution(int n) {
		dfs(n, "left", "mid", "right");
		return res;
	}

	//首先将n-1 个圆盘从left -> mid  然后将left -> right  最后将 n-1个圆盘 从mid -> right
	private static void dfs(int n, String left, String mid, String right) {
		if (n <= 0) return;
		dfs(n - 1, left, right, mid);
		move(left, right);
		dfs(n - 1, mid, left, right);
	}

	public static void move(String a, String b) {
		res.add("move from " + a + " to " + b);
	}
}
