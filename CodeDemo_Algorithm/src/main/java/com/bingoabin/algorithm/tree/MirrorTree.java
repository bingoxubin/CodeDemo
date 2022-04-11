package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/16 16:06
 */
public class MirrorTree {
	//NC72 二叉树的镜像
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4] 输出 镜像
	//分析：实现二叉树的镜像
	//思路：方式一：递归
	//     方式二：迭代
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

		Mirror1(node3);
		Mirror2(node3);
	}

	//方式一：递归 通过先序遍历 先将两个节点交换，然后进行递归遍历
	public static TreeNode Mirror1(TreeNode root) {
		if (root == null) return root;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		Mirror1(root.left);
		Mirror1(root.right);
		return root;
	}

	//方式二：迭代 通过双端队列，加入到节点中，然后进行弹出交换操作
	public static TreeNode Mirror2(TreeNode root) {
		if (root == null) return root;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			if (node.left == null) queue.offer(node.left);
			if (node.right == null) queue.offer(node.right);
		}
		return root;
	}
}
