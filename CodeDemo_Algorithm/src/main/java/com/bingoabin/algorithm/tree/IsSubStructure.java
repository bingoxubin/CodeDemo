package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2022/7/26 9:59
 */
public class IsSubStructure {
	//Leetcode 剑指 Offer 26. 树的子结构
	//示例：示例 1：
	//      输入：A = [1,2,3], B = [3,1]
	//      输出：false
	//      示例 2：
	//      输入：A = [3,4,5,1,2], B = [4,1]
	//      输出：true
	//分析：输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
	//      B是A的子结构， 即 A中有出现和B相同的结构和节点值。
	//      例如:
	//      给定的树 A:
	//        3
	//       / \
	//      4    5
	//     / \
	//    1  2
	//      给定的树 B：
	//        4
	//       /
	//      1
	//      返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
	//思路：
	public static void main(String[] args) {
		//第一棵树
		TreeNode node4 = new TreeNode(5);
		TreeNode node2 = new TreeNode(3, node4, null);
		TreeNode node3 = new TreeNode(2);
		TreeNode node1 = new TreeNode(1, node2, node3);

		//第二棵树
		TreeNode node6 = new TreeNode(5);
		TreeNode node5 = new TreeNode(3, node6, null);

		IsSubStructure isSubTree = new IsSubStructure();
		System.out.println(isSubTree.isSubStructure(node1, node5));
	}

	public boolean isSubStructure(TreeNode A, TreeNode B) {
		if (A == null && B == null) return true;
		if (A == null || B == null) return false;
		return dp(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
	}

	public boolean dp(TreeNode node1, TreeNode node2) {
		//if(node1 == null && node2 == null) return true;
		//if(node1 == null || node2 == null) return false;
		if (node2 == null) return true;
		if (node1 == null) return false;
		if (node1.val != node2.val) return false;
		return dp(node1.left, node2.left) && dp(node1.right, node2.right);
	}
}
