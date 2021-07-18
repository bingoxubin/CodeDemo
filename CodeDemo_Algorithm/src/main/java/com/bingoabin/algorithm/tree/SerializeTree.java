package com.bingoabin.algorithm.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/30 23:42
 */
public class SerializeTree {
	//剑指 Offer 37. 序列化二叉树
	//样例：输入：root = [1,2,3,null,null,4,5]  输出：[1,2,3,null,null,4,5]
	//分析：请实现两个函数，分别用来序列化和反序列化二叉树。
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
	//              1
	//         2         3
	//                 4    5
	public static void main(String[] args) {
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node3 = new TreeNode(3, node4, node5);
		TreeNode node2 = new TreeNode(2);
		TreeNode node1 = new TreeNode(1, node2, node3);

		SerializeTree serializeTree = new SerializeTree();
		System.out.println(serializeTree.serialize1(node1));
		System.out.println(serializeTree.deserialize1(serializeTree.serialize1(node1)));

		System.out.println(serializeTree.serialize2(node1));
		System.out.println(serializeTree.deserialize2(serializeTree.serialize2(node1)));

		System.out.println(Arrays.toString("1!2!#!#!3!4!#!#!5!#!#".split("!")));
	}

	//方式一：迭代方式
	//序列化
	public String serialize1(TreeNode root) {
		if (root == null) return "#";
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		StringBuilder res = new StringBuilder();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				res.append("#!");
				continue;
			}
			res.append(node.val + "!");
			queue.offer(node.left);
			queue.offer(node.right);
		}
		return res.toString();
	}

	//反序列化
	public TreeNode deserialize1(String str) {
		if (str.equals("#")) return null;
		String[] res = str.split("!");
		TreeNode root = new TreeNode(Integer.parseInt(res[0]));
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		for (int i = 1; i < res.length; i++) {
			TreeNode node = queue.poll();
			if (!"#".equals(res[i])) {
				TreeNode left = new TreeNode(Integer.parseInt(res[i]));
				node.left = left;
				queue.add(left);
			}
			if (!"#".equals(res[++i])) {
				TreeNode right = new TreeNode(Integer.parseInt(res[i]));
				node.right = right;
				queue.add(right);
			}
		}
		return root;
	}

	//方式二：递归方式
	//序列化
	//把树转化为字符串（使用DFS遍历，也是前序遍历，顺序是：根节点→左子树→右子树）
	public String serialize2(TreeNode root) {
		if (root == null) return "#";
		return root.val + "!" + serialize2(root.left) + "!" + serialize2(root.right);
	}

	//反序列化
	public TreeNode deserialize2(String str) {
		Deque<String> queue = new LinkedList<String>(Arrays.asList(str.split("!")));
		return helper(queue);
	}

	public TreeNode helper(Deque<String> queue) {
		String value = queue.poll();
		if (value.equals("#")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(value));
		root.left = helper(queue);
		root.right = helper(queue);
		return root;
	}
}
