//对称的二叉树

//题目描述
// 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

package com.bingoabin.newcoder;

public class _58BalanceBinaryTree {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		boolean isSymmetrical(TreeNode pRoot) {
			//1.空树
			if (pRoot == null) {
				return true;
			}
			//2.非空树
			return isSymmetricalBinaryTree(pRoot.left, pRoot.right);
		}

		public boolean isSymmetricalBinaryTree(TreeNode left, TreeNode right) {
			if (left == null && right == null) {
				return true;
			}
			if (left == null && right != null) {
				return false;
			}
			if (left != null && right == null) {
				return false;
			} else {
				if (left.val != right.val) {
					return false;
				}
				return isSymmetricalBinaryTree(left.left, right.right) && isSymmetricalBinaryTree(left.right, right.left);
			}
		}

	}
}
