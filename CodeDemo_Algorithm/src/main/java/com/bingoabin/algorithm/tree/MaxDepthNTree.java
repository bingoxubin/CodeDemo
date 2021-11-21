package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/11/21 7:06 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxDepthNTree {
	//Leetcode 559. N 叉树的最大深度
	//示例：输入：root = [1,null,3,2,4,null,5,6]  输出：3
	//分析：给定一个 N 叉树，找到其最大深度。最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
	//思路：
	public static void main(String[] args) {
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		ArrayList<Node> nodes1 = new ArrayList<>();
		nodes1.add(node5);
		nodes1.add(node6);
		Node node3 = new Node(3, nodes1);

		Node node2 = new Node(2);
		Node node4 = new Node(4);
		List<Node> nodes = new ArrayList<>();
		nodes.add(node3);
		nodes.add(node2);
		nodes.add(node4);
		Node node1 = new Node(1, nodes);

		MaxDepthNTree maxDepthNTree = new MaxDepthNTree();
		System.out.println(maxDepthNTree.maxDepth1(node1));
		System.out.println(maxDepthNTree.maxDepth2(node1));
	}

	//广度 层序遍历
	public int maxDepth1(Node root) {
		int depth = 0;
		if (root == null) return 0;
		Deque<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			depth++;
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				for (Node temp : node.children) {
					queue.offer(temp);
				}
			}
		}
		return depth;
	}

	//深度 递归
	public int maxDepth2(Node root) {
		if (root == null) return 0;
		int max = 0;
		for (Node child : root.children) {
			max = Math.max(max, maxDepth2(child));
		}
		return max;
	}
}

class Node {
	public int val;
	public List<Node> children;

	public Node() {
	}

	public Node(int val) {
		this.val = val;
	}

	public Node(int val, List<Node> children) {
		this.val = val;
		this.children = children;
	}
}


