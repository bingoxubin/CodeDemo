package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/13 1:02
 */
public class LevelOrder {
	//NC15 二叉树的层序遍历
	//样例：输入 {1,2,3}  返回值：[[1],[2,3]]
	//分析：实现二叉树的层序遍历
	//思路：方式一：递归方式,定义一个结果集，然后根据层数，当层数==list.size 则往res中添加一个new arraylist,然后根据层数res.get(depth).add(root.val) 添加值
	//     方式二：迭代方式
	public static void main(String[] args) {
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node1 = new TreeNode(1, node2, node3);
		LevelOrder levelOrder = new LevelOrder();
		System.out.println(levelOrder.levelOrder1(node1));
		System.out.println(levelOrder.levelOrder2(node1));
	}

	//方式一：递归方式,定义一个结果集，然后根据层数，当层数==list.size 则往res中添加一个new arraylist,然后根据层数res.get(depth).add(root.val) 添加值
	public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null) return res;
		dfs(root, 0, res);
		return res;
	}

	public void dfs(TreeNode root, int depth, ArrayList<ArrayList<Integer>> res) {
		if (depth == res.size()) {
			res.add(new ArrayList<Integer>());
		}
		res.get(depth).add(root.val);
		if (root.left != null) dfs(root.left, depth + 1, res);
		if (root.right != null) dfs(root.right, depth + 1, res);
	}

	//方式二：迭代方式,通过双端队列的方式，先将头结点加入到队列中，如果队列中不为空，计算该层的节点数，然后弹出节点，加入到list中，然后加入下一层的节点，不断循环
	public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (root == null) return res;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
			}
			res.add(list);
		}
		return res;
	}
}
