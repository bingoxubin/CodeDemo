package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/21 16:05
 */
public class TargetPathHas {
	//NC9 二叉树中是否存在节点和为指定值的路径
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],14 输出：true
	//分析：计算值为指定和的路径
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

		System.out.println(hasPathSum1(node3, 14));
		System.out.println(hasPathSum2(node3, 100));
	}

	//方式一：通过迭代方式  创建两个队列，treenode队列 和sum队列，当node的左右都为null的时候比较sum目标值和累加的值是否一样
	public static boolean hasPathSum1(TreeNode root, int sum) {
		if (root == null) return false;
		Deque<TreeNode> nodequeue = new LinkedList<TreeNode>();
		Deque<Integer> sumqueue = new LinkedList<Integer>();
		nodequeue.offer(root);
		sumqueue.offer(root.val);
		while (!nodequeue.isEmpty()) {
			TreeNode node = nodequeue.poll();
			int ans = sumqueue.poll();
			if (node.left == null && node.right == null) {
				if (sum == ans) {
					return true;
				}
			}
			if (node.left != null) {
				nodequeue.offer(node.left);
				sumqueue.offer(ans + root.left.val);
			}
			if (node.right != null) {
				nodequeue.offer(node.right);
				sumqueue.offer(ans + root.right.val);
			}
		}
		return false;
	}

	//方式二：通过递归方式
	public static boolean hasPathSum2(TreeNode root, int sum) {
		if (root == null) return false;
		if (root.left == null && root.right == null) return root.val == sum;
		return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
	}
}
