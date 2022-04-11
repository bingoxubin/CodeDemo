//二叉树的深度
//输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

//递归：
// 	如果树为空，则返回0。
// 	否则，该树的树高为该节点的左右子树高度的大者加1（1为该节点这一层）
//非递归：
// 	使用队列完成二叉树的层次遍历（BFS）
package com.bingoabin.newcoder;

import java.util.LinkedList;

public class _38BinaryTreeDepth {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public int TreeDepth(TreeNode root) {
			if (root == null) {
				return 0;
			}
			int nLelt = TreeDepth(root.left);
			int nRight = TreeDepth(root.right);
			return nLelt > nRight ? (nLelt + 1) : (nRight + 1);
		}
	}

	public class Solution1 {
		public int TreeDepth(TreeNode root) {
			if (root == null) {
				return 0;
			}
			return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
		}
	}

	public class Solution2 {
		public int TreeDepth(TreeNode root) {
			if (root == null) {
				return 0;
			}
			LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
			int length = 0;
			queue.add(root);
			int start = 0;
			int end = 1;
			TreeNode temp = null;
			while (!queue.isEmpty()) {
				temp = queue.remove();
				start++;
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}
				if (start == end) {
					length++;
					start = 0;
					end = queue.size();
				}
			}
			return length;
		}
	}
}
