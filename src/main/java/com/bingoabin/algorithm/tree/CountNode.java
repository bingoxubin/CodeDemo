package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/26 21:22
 */
public class CountNode {
	//统计二叉树的节点数
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4] 输出：11
	//分析：计算二叉树的节点数
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//              3
	//         5         1
	//      6     2    0    8
	//          7  4  9      10
	public static void main(String[] args) {
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node4 = new TreeNode(4);
		TreeNode node9 = new TreeNode(9);
		TreeNode node0 = new TreeNode(0, node9, null);
		TreeNode node10 = new TreeNode(10);
		TreeNode node8 = new TreeNode(8, null, node10);
		TreeNode node2 = new TreeNode(2, node7, node4);
		TreeNode node5 = new TreeNode(5, node6, node2);
		TreeNode node1 = new TreeNode(1, node0, node8);
		TreeNode node3 = new TreeNode(3, node5, node1);

		CountNode countNode = new CountNode();
		System.out.println(countNode.countNode1(node3));
		System.out.println(countNode.countNode2(node3));
	}

	//方式一：通过迭代方式
	public int countNode1(TreeNode node) {
		if (node == null) return 0;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(node);
		int res = 0;
		while (!queue.isEmpty()) {
			TreeNode nodetmp = queue.poll();
			res++;
			if (nodetmp.left != null) queue.offer(nodetmp.left);
			if (nodetmp.right != null) queue.offer(nodetmp.right);
		}
		return res;
	}

	//方式二：通过递归方式
	int count = 0;

	public int countNode2(TreeNode node) {
		if (node == null) return 0;
		countNode2(node.left);
		count++;
		countNode2(node.right);
		return count;
	}
}
