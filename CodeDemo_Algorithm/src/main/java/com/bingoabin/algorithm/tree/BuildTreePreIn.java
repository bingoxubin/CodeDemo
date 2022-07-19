package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2022/7/19 10:14
 */
public class BuildTreePreIn {
	//Leetcode 剑指 Offer 07. 重建二叉树
	//示例：示例 1:
	//      Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
	//      Output: [3,9,20,null,null,15,7]
	//      示例 2:
	//      Input: preorder = [-1], inorder = [-1]
	//      Output: [-1]
	//分析：输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
	//      假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	//思路：
	public static void main(String[] args) {
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		BuildTreePreIn buildTreePreIn = new BuildTreePreIn();
		TreeNode res = buildTreePreIn.buildTree(preorder, inorder);
		System.out.println(res.val);
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode helper(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
		if (pstart > pend || istart > iend) return null;
		TreeNode res = new TreeNode(preorder[pstart]);
		int index = 0;
		while (inorder[istart + index] != preorder[pstart]) {
			index++;
		}
		res.left = helper(preorder, pstart + 1, pstart + index, inorder, istart, istart + index - 1);
		res.right = helper(preorder, pstart + index + 1, pend, inorder, istart + index + 1, iend);
		return res;
	}
}
