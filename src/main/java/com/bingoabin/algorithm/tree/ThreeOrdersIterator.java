package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/25 13:22
 */
public class ThreeOrdersIterator {
	//样例：输入 {1,2,3}  返回值：[[1,2,3],[2,1,3],[2,3,1]]
	//分析：分别按照二叉树先序，中序和后序打印所有的节点。
	//思路：通过迭代的方式
	public static void main(String[] args) {
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node1 = new TreeNode(1, node2, node3);
		ThreeOrdersIterator threeOrders = new ThreeOrdersIterator();
		System.out.println(threeOrders.preOrder1(node1));
		System.out.println(threeOrders.preOrder2(node1));
		System.out.println(threeOrders.preOrder3(node1));
	}

	//先序遍历
	public ArrayList<Integer> preOrder1(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				res.add(root.val);
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			root = root.right;
		}
		return res;
	}

	//中序遍历
	public ArrayList<Integer> preOrder2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			res.add(root.val);
			root = root.right;
		}
		return res;
	}

	//后续遍历
	public ArrayList<Integer> preOrder3(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode lastvisit = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				if (root.right == null || root.right == lastvisit) {
					res.add(root.val);
					lastvisit = root;
					root = null;
				} else {
					stack.push(root);
					root = root.right;
				}
			}
		}
		return res;
	}
}
