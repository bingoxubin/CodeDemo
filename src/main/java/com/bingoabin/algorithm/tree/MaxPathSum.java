package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/18 2:18
 */
public class MaxPathSum {
	//NC6 二叉树的最大路径和
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4] 输出 26
	//分析：给定一个二叉树，返回该二叉树的最长路径和
	//思路：递归方式进行计算
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

		System.out.println(maxPathSum(node3));
	}

	static int res = 0;

	public static int maxPathSum(TreeNode root) {
		dfs(root);
		return res;
	}

	//求其中一个节点的最大贡献，那么就是左边的最大值 或者右边的最大值 + 当前节点值
	public static int dfs(TreeNode root) {
		if (root == null) return 0;
		int left = Math.max(dfs(root.left), 0);
		int right = Math.max(dfs(root.right), 0);
		res = Math.max(res, left + right + root.val);
		return Math.max(left, right) + root.val;
	}
}
