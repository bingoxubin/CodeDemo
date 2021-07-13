package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/24 0:28
 */
public class SingleValueTree {
	//LeetCode 965. 单值二叉树
	//样例：输入 [1,1,1,1,1,1,1] 输出：true
	//分析：判断树中是否都是等值的
	//思路：通过递归方式
	//              1
	//         1         1
	//      1     1    1    1
	public static void main(String[] args) {
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(1);
		TreeNode node6 = new TreeNode(1);
		TreeNode node7 = new TreeNode(1);
		TreeNode node2 = new TreeNode(1, node4, node5);
		TreeNode node3 = new TreeNode(1, node6, node7);
		TreeNode node1 = new TreeNode(1, node2, node3);

		System.out.println(isUnivalTree1(node1));
		SingleValueTree singleValueTree = new SingleValueTree();

		TreeNode node8 = new TreeNode(0);
		System.out.println(singleValueTree.isUnivalTree2(node8));
	}

	//递归实现1
	public static boolean isUnivalTree1(TreeNode root) {
		if (root == null) return true;
		if (root.left != null && root.left.val != root.val) return false;
		if (root.right != null && root.right.val != root.val) return false;
		return isUnivalTree1(root.left) && isUnivalTree1(root.right);
	}

	//递归实现2
	boolean res = true;

	public boolean isUnivalTree2(TreeNode root) {
		if (root == null) return true;
		int value = root.val;
		dfs(root, value);
		return res;
	}

	private void dfs(TreeNode root, int value) {
		if (root == null || !res) return;
		if (root.val != value) {
			res = false;
			return;
		}
		dfs(root.left, value);
		dfs(root.right, value);
	}
}
