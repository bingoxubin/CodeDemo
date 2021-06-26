package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/26 21:00
 */
public class KthMinNode {
	//NC81 二叉搜索树的第k个节点
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],3 输出：2
	//分析：给定一棵二叉搜索树，请找出其中的第k小的结点。
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//              3
	//         5         1
	//      6     2    0    8
	//          7  4
	public static void main(String[] args) {
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node4 = new TreeNode(4);
		TreeNode node0 = new TreeNode(0);
		TreeNode node8 = new TreeNode(8);
		TreeNode node2 = new TreeNode(2, node7, node4);
		TreeNode node5 = new TreeNode(5, node6, node2);
		TreeNode node1 = new TreeNode(1, node0, node8);
		TreeNode node3 = new TreeNode(3, node5, node1);

		KthMinNode kthMinNode = new KthMinNode();

		System.out.println(kthMinNode.KthNode1(node3, 3).val);
		System.out.println(kthMinNode.KthNode2(node3, 3).val);
	}

	//递归的方式
	TreeNode res;
	int count = 0;

	public TreeNode KthNode1(TreeNode pRoot, int k) {
		dfs(pRoot, k);
		return res;
	}

	public void dfs(TreeNode root, int k) {
		if (root == null) return;
		dfs(root.left, k);
		if (++count == k) {  //注意 ++count
			res = root;
			return;
		}
		dfs(root.right, k);
	}

	//迭代的方式
	public TreeNode KthNode2(TreeNode pRoot, int k) {
		if (pRoot == null) return pRoot;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		while (!stack.isEmpty() || pRoot != null) {
			while (pRoot != null) {
				stack.push(pRoot);
				pRoot = pRoot.left;
			}
			pRoot = stack.pop();
			if (--k == 0) {   //注意 --k
				return pRoot;
			}
			pRoot = pRoot.right;
		}
		return null;
	}
}
