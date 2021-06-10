package com.bingoabin.algorithm.tree;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/5/17 12:38 上午
 */
public class IsBrotherNode {
	//Leetcode.993 二叉树的堂兄弟节点  https://leetcode-cn.com/problems/cousins-in-binary-tree/
	//样例：root = [1,2,3,null,4,null,5], x = 5, y = 4  返回 true
	//分析：给一棵树，取其中的两个节点，判断他们是否是堂兄弟节点，堂兄弟节点的要求就是，在同一层，并且父节点不相同
	//思路：创建两个hashmap，分别记下每个节点的父节点，以及层数
	public static void main(String[] args) {
		IsBrotherNode isBrotherNode = new IsBrotherNode();
		isBrotherNode.test();
	}

	public void test() {
		TreeNode node5 = new TreeNode(5);
		TreeNode node4 = new TreeNode(4);
		TreeNode node2 = new TreeNode(2, null, node4);
		TreeNode node3 = new TreeNode(3, null, node5);
		TreeNode node1 = new TreeNode(1, node2, node3);
		System.out.println(isCousins(node1, 5, 4));
	}

	//定义父节点 深度的hashmap
	HashMap<Integer, TreeNode> parent;
	HashMap<Integer, Integer> depth;

	//主体方法，实现返回两个节点是否为堂兄弟节点
	public boolean isCousins(TreeNode root, int x, int y) {
		depth = new HashMap<>();
		parent = new HashMap<>();
		dfs(root, null);
		return depth.get(x).equals(depth.get(y)) && parent.get(x) != parent.get(y);
	}

	//通过前序遍历，求出每个节点的深度，以及父节点
	private void dfs(TreeNode root, TreeNode pre) {
		if (root == null) {
			return;
		}
		depth.put(root.val, pre == null ? 0 : depth.get(pre.val) + 1);
		parent.put(root.val, pre);
		dfs(root.left, root);
		dfs(root.right, root);
	}
}
