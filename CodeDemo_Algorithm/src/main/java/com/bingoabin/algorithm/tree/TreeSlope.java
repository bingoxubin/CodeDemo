package com.bingoabin.algorithm.tree;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/18 10:16 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TreeSlope {
	//Leetcode 563. 二叉树的坡度
	//示例：输入：root = [4,2,9,3,5,null,7]
	//     输出：15
	//     解释：
	//     节点 3 的坡度：|0-0| = 0（没有子节点）
	//     节点 5 的坡度：|0-0| = 0（没有子节点）
	//     节点 7 的坡度：|0-0| = 0（没有子节点）
	//     节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
	//     节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
	//     节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 16 ）
	//     坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
	//分析：给定一个二叉树，计算 整个树 的坡度 。
	//     一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
	//     整个树 的坡度就是其所有节点的坡度之和。
	//思路：递归方式
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(4);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(9);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;

		TreeSlope treeSlope = new TreeSlope();
		out.println(treeSlope.findTilt(node1));
	}

	int res = 0;

	public int findTilt(TreeNode root) {
		dfs(root);
		return res;
	}

	public int dfs(TreeNode root) {
		if (root == null) return 0;
		int left = dfs(root.left);
		int right = dfs(root.right);
		res += Math.abs(left - right);
		return left + right + root.val;
	}
}
