package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/26 11:19
 */
public class CompleteBinaryTreeNum {
	//NC84 完全二叉树节点数
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],14 输出：true
	//分析：计算完全二叉树的节点个数
	//思路：通过递归的方式
	//              3
	//         5         1
	//      6     2    0    8
	//   9  10   7  4
	public static void main(String[] args) {
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node6 = new TreeNode(6, node9, node10);
		TreeNode node7 = new TreeNode(7);
		TreeNode node4 = new TreeNode(4);
		TreeNode node0 = new TreeNode(0);
		TreeNode node8 = new TreeNode(8);
		TreeNode node2 = new TreeNode(2, node7, node4);
		TreeNode node5 = new TreeNode(5, node6, node2);
		TreeNode node1 = new TreeNode(1, node0, node8);
		TreeNode node3 = new TreeNode(3, node5, node1);

		CompleteBinaryTreeNum completeBinaryTreeNum = new CompleteBinaryTreeNum();
		System.out.println(completeBinaryTreeNum.nodeNum(node3));
	}

	public int nodeNum(TreeNode root) {
		if (root == null) return 0;
		int left = getLevel(root.left);
		int right = getLevel(root.right);
		if (left == right) {
			return nodeNum(root.right) + (1 << left);
		} else {
			return nodeNum(root.left) + (1 << right);
		}
	}

	public int getLevel(TreeNode root) {
		int count = 0;
		while (root != null) {
			count++;
			root = root.left;
		}
		return count;
	}
}
