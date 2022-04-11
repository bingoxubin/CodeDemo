package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/18 16:57
 */
public class MaxDepth {
	//NC13 二叉树的最大深度
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],5,1 输出：4
	//分析：计算二叉树的最大深度
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

		System.out.println(maxDepth1(node3));
		System.out.println(maxDepth2(node3));
	}

	//方式一：通过迭代方式
	public static int maxDepth1(TreeNode root) {
		if(root == null) return 0;
		int res = 0;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			res++;
			for(int i = 0;i<size;i++){
				TreeNode node = queue.poll();
				if(node.left != null) queue.offer(node.left);
				if(node.right != null) queue.offer(node.right);
			}
		}
		return res;
	}

	//方式二：通过递归方式
	public static int maxDepth2(TreeNode root) {
		if(root == null) return 0;
		int left = maxDepth2(root.left);
		int right = maxDepth2(root.right);
		return Math.max(left,right) + 1;
	}
}
