package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/18 20:00
 */
public class AllPathSum {
	//NC5 二叉树根节点到叶子节点的所有路径和
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],5,1 输出：8035
	//分析：计算从根到叶子节点的所有路径和
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//              3
	//         5         1
	//      6     2    0    8
	//          7  4
	// 结果为 356 + 3527 + 3524 + 310 + 318 = 8035
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

		System.out.println(sumNumbers1(node3));
		System.out.println(sumNumbers2(node3));
	}

	//方式一：通过迭代方式 通过节点队列  跟  数值队列
	public static int sumNumbers1(TreeNode root) {
		if (root == null) return 0;
		int sum = 0;
		Deque<Integer> value = new LinkedList<Integer>();
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		value.offer(root.val);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			int num = value.poll();
			if (node.left == null && node.right == null) {
				sum += num;
			} else {
				if (node.left != null) {
					queue.offer(node.left);
					value.offer(10 * num + node.left.val);
				}
				if (node.right != null) {
					queue.offer(node.right);
					value.offer(10 * num + node.right.val);
				}
			}
		}
		return sum;
	}

	//方式二：通过递归方式
	static int result = 0;

	public static int sumNumbers2(TreeNode root) {
		dfs(root, 0);
		return result;
	}

	public static void dfs(TreeNode root, int sum) {
		if (root == null) return;
		sum = 10 * sum + root.val;
		if (root.left == null && root.right == null) result += sum;
		dfs(root.left, sum);
		dfs(root.right, sum);
	}
}
