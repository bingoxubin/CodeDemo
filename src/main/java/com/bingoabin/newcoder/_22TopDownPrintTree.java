//从上往下打印二叉树
//从上往下打印出二叉树的每个节点，同层节点从左至右打印。

//思路：
//广度优先搜索遍历（BFS）：
// 	利用队列，若树不为空，则进队根节点。
// 	当队列不为空时，执行以下循环：
// 		1.出队一个元素，并将其节点值加入到list中；
// 		2.若当前元素的左孩子不为空，则将其左子孩子入队。
// 		3.若当前元素的右孩子不为空，则将其右孩子入队。

package com.bingoabin.newcoder;

import java.util.ArrayList;

public class _22TopDownPrintTree {
	public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public class Solution {
		public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
			ArrayList<Integer> result = new ArrayList();
			ArrayList<TreeNode> treenode = new ArrayList();
			if (root == null) return result;
			treenode.add(root);
			while (treenode.size() != 0) {
				TreeNode temp = treenode.remove(0);
				if (temp.left != null) treenode.add(temp.left);
				if (temp.right != null) treenode.add(temp.right);
				result.add(temp.val);
			}
			return result;
		}
	}
}
