package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2022/7/30 11:29
 * @Description: 验证二叉树的后续遍历
 */
public class VertifyPostOrder {
	//Leetcode 剑指 Offer 33. 二叉搜索树的后序遍历序列
	//示例：示例 1：
	//      输入: [1,6,3,2,5]
	//      输出: false
	//      示例 2：
	//      输入: [1,3,2,6,5]
	//      输出: true
	//分析：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
	//      参考以下这颗二叉搜索树：
	//      5
	//     / \
	//    2   6
	//   / \
	//  1   3
	//思路：
	public static void main(String[] args){
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2,node1,node3);
		TreeNode node6 = new TreeNode(6);
		TreeNode node5 = new TreeNode(5,node2,node6);

		int[] postorder = {1, 6, 3, 2, 5};
		int[] postorder1 = {1, 3, 2, 6, 5};
		VertifyPostOrder vertiryPostOrder = new VertifyPostOrder();
		System.out.println(vertiryPostOrder.verityPostorder(postorder));
		// System.out.println(vertiryPostOrder.verityPostorder(postorder1));
	}

	public boolean verityPostorder(int[] postorder){
		Deque<Integer> stack = new LinkedList<>();
		int pre = Integer.MAX_VALUE;
		for(int i = postorder.length - 1;i>= 0;i--){
			int cur = postorder[i];
			while(!stack.isEmpty() && stack.peek() > cur){
				pre = stack.pop();
			}
			if(cur > pre) return false;
			stack.push(cur);
		}
		return true;
	}
}

