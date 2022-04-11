package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/26 11:30
 */
public class MergeTree {
	//NC117 合并二叉树
	//样例：
	//分析：将两棵树进行合并
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//两颗二叉树是:
	// Tree 1
	//      1
	//     / \
	//    3   2
	//   /
	//  5
	//
	// Tree 2
	//    2
	//   / \
	//  1   3
	//   \   \
	//    4   7
	//
	// 合并后的树为
	//     3
	//    / \
	//   4   5
	//  / \    \
	// 5  4    7
	public static void main(String[] args) {
		//第一棵树
		TreeNode node4 = new TreeNode(5);
		TreeNode node2 = new TreeNode(3, node4, null);
		TreeNode node3 = new TreeNode(2);
		TreeNode node1 = new TreeNode(1, node2, node3);

		//第二棵树
		TreeNode node8 = new TreeNode(4);
		TreeNode node9 = new TreeNode(7);
		TreeNode node6 = new TreeNode(1, null, node8);
		TreeNode node7 = new TreeNode(3, null, node9);
		TreeNode node5 = new TreeNode(2, node6, node7);

		MergeTree mergeTree = new MergeTree();
		//System.out.println(mergeTree.mergeTrees1(node1, node5).val);
		System.out.println(mergeTree.mergeTrees2(node1, node5).val);
	}

	//迭代方式
	public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
		if (t1 == null) return t2;
		if (t2 == null) return t1;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(t1);
		queue.offer(t2);
		while (!queue.isEmpty()) {
			TreeNode node1 = queue.poll();
			TreeNode node2 = queue.poll();
			node1.val += node2.val;
			if (node1.left != null && node2.left != null) {
				queue.offer(node1.left);
				queue.offer(node2.left);
			} else if (node1.left == null) {
				node1.left = node2.left;
			}
			if (node1.right != null && node2.right != null) {
				queue.offer(node1.right);
				queue.offer(node2.right);
			} else if (node1.right == null) {
				node1.right = node2.right;
			}
		}
		return t1;
	}

	//递归方式
	public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
		if (t1 == null) return t2;
		if (t2 == null) return t1;
		TreeNode root = new TreeNode(t1.val + t2.val);
		root.left = mergeTrees2(t1.left, t2.left);
		root.right = mergeTrees2(t1.right, t2.right);
		return root;
	}
}


