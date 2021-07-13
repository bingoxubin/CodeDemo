package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/26 11:50
 */
public class IsSubTree {
	//NC98 判断t1树中是否有与t2树拓扑结构完全相同的子树
	//样例：
	//分析：判断一棵树是否是另一棵树的子树
	//思路：通过递归的方式
	//两颗二叉树是:
	// Tree 1
	//      1
	//     / \
	//    3   2
	//   /
	//  5
	//
	// Tree 2
	//    3
	//   /
	//  5
	public static void main(String[] args) {
		//第一棵树
		TreeNode node4 = new TreeNode(5);
		TreeNode node2 = new TreeNode(3, node4, null);
		TreeNode node3 = new TreeNode(2);
		TreeNode node1 = new TreeNode(1, node2, node3);

		//第二棵树
		TreeNode node6 = new TreeNode(5);
		TreeNode node5 = new TreeNode(3, node6, null);

		IsSubTree isSubTree = new IsSubTree();
		System.out.println(isSubTree.isContains(node1, node5));
	}

	//递归判断，递归root1中的所有节点
	public boolean isContains(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) return true;
		if (root1 == null || root2 == null) return false;
		return helper(root1, root2) || isContains(root1.left, root2) || isContains(root1.right, root2);
	}

	//判断两棵树是否相等
	public boolean helper(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) return true;
		if (node1 == null || node2 == null) return false;

		if (node1.val != node2.val) return false;
		return helper(node1.left, node2.left) && helper(node1.right, node2.right);
	}
}
