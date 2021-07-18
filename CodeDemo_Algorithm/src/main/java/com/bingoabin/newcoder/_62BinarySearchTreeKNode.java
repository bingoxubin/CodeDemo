//二叉搜索树的第K个节点
//给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
package com.bingoabin.newcoder;

public class _62BinarySearchTreeKNode {
	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		int count = 0;

		TreeNode KthNode(TreeNode pRoot, int k) {
			// 	   5
			// 	3	7
			// 2	4	6  8
			if (pRoot != null) {
				TreeNode left = KthNode(pRoot.left, k);
				if (left != null) {
					return left;
				}

				count++;
				if (count == k) {
					return pRoot;
				}
				TreeNode right = KthNode(pRoot.right, k);
				if (right != null) {
					return right;
				}
			}
			return null;
		}
	}
}
