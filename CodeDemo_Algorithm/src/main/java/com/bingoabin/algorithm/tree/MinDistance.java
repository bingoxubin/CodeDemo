package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/24 9:57
 */
public class MinDistance {
	//LeetCode 783. 二叉搜索树节点最小距离
	//样例：输入 [4,2,6,1,3,null,null] 输出：1
	//分析：任意两个链接节点的差值最小值
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//          4
	//         /   \
	//       2      6
	//      / \
	//     1   3
	// 结果为 1
	public static void main(String[] args) {
		TreeNode node6 = new TreeNode(6);
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2,node1,node3);
		TreeNode node4 = new TreeNode(4,node2,node6);

		MinDistance minDistance = new MinDistance();
		System.out.println(minDistance.minDiffInBST1(node4));
		System.out.println(minDistance.minDiffInBST2(node4));
	}

	//迭代的方式
	public static int minDiffInBST1(TreeNode root) {
		if(root == null) return 0;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode pre = null;
		int min = Integer.MAX_VALUE;
		while(!stack.isEmpty() || root != null){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if(pre != null) min = Math.min(min,root.val - pre.val);
			pre = root;
			root = root.right;
		}
		return min;
	}

	//递归的方式
	static TreeNode pre;
	static int min = Integer.MAX_VALUE;
	public static int minDiffInBST2(TreeNode root) {
		dfs(root);
		return min;
	}

	public static void dfs(TreeNode root){
		if(root == null) return;
		dfs(root.left);
		if(pre != null) min = Math.min(root.val - pre.val,min);
		pre = root;
		dfs(root.right);
	}
}
