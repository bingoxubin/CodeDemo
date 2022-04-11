package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/18 19:53
 */
public class IsBlancedTree {
	//NC62 平衡二叉树
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],5,1 输出：4
	//分析：判断是否是平衡二叉树
	//思路：通过递归比较高度
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

		System.out.println(IsBalanced_Solution(node3));
	}

	public static boolean IsBalanced_Solution(TreeNode root) {
		if (root == null) return true;
		int left = getHigh(root.left);
		int right = getHigh(root.right);
		return Math.abs(left - right) <= 1 ? IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) : false;
	}

	public static int getHigh(TreeNode root) {
		if (root == null) return 0;
		int left = getHigh(root.left);
		int right = getHigh(root.right);
		return Math.max(left, right) + 1;
	}
}
