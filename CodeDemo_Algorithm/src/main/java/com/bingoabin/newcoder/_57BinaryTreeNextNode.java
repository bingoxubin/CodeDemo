//二叉树的下一个节点
//给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
package com.bingoabin.newcoder;

public class _57BinaryTreeNextNode {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left = null;
		TreeLinkNode right = null;
		TreeLinkNode next = null;

		TreeLinkNode(int val) {
			this.val = val;
		}
	}

	public class Solution1 {
		public TreeLinkNode GetNext(TreeLinkNode pNode) {
			if (pNode == null) {
				return null;
			}
			if (pNode.right != null) {
				pNode = pNode.right;
				while (pNode.left != null) {
					pNode = pNode.left;
				}
				return pNode;
			}
			while (pNode.next != null) {
				if (pNode == pNode.next.left) {
					return pNode.next;
				}
				pNode = pNode.next;
			}
			return null;
		}
	}
}
