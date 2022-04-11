package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2021/6/18 16:44
 */
public class RightSideTree {
	//NC136 输出二叉树的右视图
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],5,1 输出：3 1 8 4
	//分析：计算二叉树的右视图
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//              3
	//         5         1
	//      6     2    0    8
	//          7  4
	public static void main(String[] args) {
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node4 = new TreeNode(4);
		TreeNode node0 = new TreeNode(0);
		TreeNode node8 = new TreeNode(8);
		TreeNode node2 = new TreeNode(2, node7, node4);
		TreeNode node5 = new TreeNode(5, node6, node2);
		TreeNode node1 = new TreeNode(1, node0, node8);
		TreeNode node3 = new TreeNode(3, node5, node1);

		System.out.println(rightSideView1(node3));
		System.out.println(rightSideView2(node3));
	}

	//方式一：通过迭代方式 输出i = size - 1的时候的值
	public static List<Integer> rightSideView1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
				if (i == size - 1) res.add(node.val);
			}
		}
		return res;
	}

	//方式二：通过递归方式,将递归顺序  修改为root.right  root.left
	static List<Integer> res = new ArrayList<Integer>();

	public static List<Integer> rightSideView2(TreeNode root) {
		dfs(root, 0);
		return res;
	}

	public static void dfs(TreeNode root, int depth) {
		if (root == null) return;
		if (res.size() == depth) res.add(root.val);
		dfs(root.right, depth + 1);
		dfs(root.left, depth + 1);
	}
}
