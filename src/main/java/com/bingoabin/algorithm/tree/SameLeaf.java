package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/10 9:45 上午
 */
public class SameLeaf {
	//Leetcode.872 叶子相似的树  https://leetcode-cn.com/problems/leaf-similar-trees/
	//样例：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]  返回 true
	//分析：给你两棵树，判断树的根节点  是否按序一致
	//思路：用前序遍历的方式将根节点加入到list中，比较两棵树的list是否一致，一致返回true，不一致范围false
	public static void main(String[] args) {
		//构建root1的树
		TreeNode tmp6 = new TreeNode(6);
		TreeNode tmp7 = new TreeNode(7);
		TreeNode tmp4 = new TreeNode(4);
		TreeNode tmp9 = new TreeNode(9);
		TreeNode tmp8 = new TreeNode(8);
		TreeNode tmp2 = new TreeNode(2, tmp7, tmp4);
		TreeNode tmp5 = new TreeNode(5, tmp6, tmp2);
		TreeNode tmp1 = new TreeNode(1, tmp9, tmp8);
		TreeNode tmp3 = new TreeNode(3, tmp5, tmp1);
		TreeNode root1 = tmp3;

		//构建root2的树
		TreeNode temp6 = new TreeNode(6);
		TreeNode temp7 = new TreeNode(7);
		TreeNode temp4 = new TreeNode(4);
		TreeNode temp9 = new TreeNode(9);
		TreeNode temp8 = new TreeNode(8);
		TreeNode temp5 = new TreeNode(5, temp6, temp7);
		TreeNode temp2 = new TreeNode(2, temp9, temp8);
		TreeNode temp1 = new TreeNode(1, temp4, temp2);
		TreeNode temp3 = new TreeNode(3, temp5, temp1);
		TreeNode root2 = temp3;

		System.out.println(leafSimilar(root1, root2));
	}

	public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		if (root1 != null) {
			dfs(root1, list1);
		}
		if (root2 != null) {
			dfs(root2, list2);
		}
		return list1.equals(list2);
	}

	//先序遍历
	public static void dfs(TreeNode root, List<Integer> list) {
		if (root.left == null && root.right == null) {
			list.add(root.val);
		}
		if (root.left != null) {
			dfs(root.left, list);
		}
		if (root.right != null) {
			dfs(root.right, list);
		}
	}

	//定义TreeNode
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode() {
		}

		public TreeNode(int val) {
			this.val = val;
		}

		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}




