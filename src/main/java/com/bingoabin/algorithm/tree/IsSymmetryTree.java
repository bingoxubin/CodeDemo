package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/21 14:53
 */
public class IsSymmetryTree {
	//NC16 判断二叉树是否对称
	//样例：输入 [3,5,6,2,2,6] 输出：true
	//分析：判断二叉树是否对称
	//思路：通过递归遍历
	//              3
	//         5         5
	//      6     2    2    6
	public static void main(String[] args) {
		TreeNode node4 = new TreeNode(6);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(2);
		TreeNode node7 = new TreeNode(6);
		TreeNode node2 = new TreeNode(5, node4, node5);
		TreeNode node3 = new TreeNode(5, node6, node7);
		TreeNode node1 = new TreeNode(3, node2, node3);

		System.out.println(isSymmetric(node1));
	}

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		return isEqual(root.left, root.right);
	}

	public static boolean isEqual(TreeNode left, TreeNode right) {
		if (left == null && right == null) return true;
		if (left == null || right == null) return false;
		return left.val == right.val && isEqual(left.left, right.right) && isEqual(left.right, right.left);
	}
}
