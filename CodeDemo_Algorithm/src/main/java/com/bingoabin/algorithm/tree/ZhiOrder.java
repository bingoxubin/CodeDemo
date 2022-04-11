package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2021/6/16 15:50
 */
public class ZhiOrder {
	//NC14 二叉树的之字形层序遍历
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4] 输出 [[3], [1, 5], [6, 2, 0, 8], [4, 7]]
	//分析：给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
	//思路：通过迭代法  进行按层遍历
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

		System.out.println(zigzagLevelOrder(node3));
	}

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		//定义结果集
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		//双端队列
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		//定义层数
		int count = 0;
		while (!queue.isEmpty()) {
			count++;
			//从队列中取出多少个数
			int size = queue.size();
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				//如果层数为奇数正常排列
				if (count % 2 == 1) {
					ans.add(node.val);
				} else { //如果层数为偶数  倒着排
					ans.add(0, node.val);
				}
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
			}
			res.add(ans);
		}
		return res;
	}
}
