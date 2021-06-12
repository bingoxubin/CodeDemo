package com.bingoabin.algorithm.tree;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/6/10 1:59 下午
 */
public class ThreeOrders {
	//NC45 二叉树前中后序遍历
	//样例：输入 {1,2,3}  返回值：[[1,2,3],[2,1,3],[2,3,1]]
	//分析：分别按照二叉树先序，中序和后序打印所有的节点。
	//思路：通过递归方式，实现树的前中后序遍历
	public static void main(String[] args) {
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node1 = new TreeNode(1, node2, node3);
		ThreeOrders threeOrders = new ThreeOrders();
		System.out.println(Arrays.deepToString(threeOrders.threeOrders(node1)));
		System.out.println(Arrays.deepToString(threeOrders.threeOrders1(node1)));
	}

	int pre = 0;
	int in = 0;
	int sub = 0;

	public int[][] threeOrders(TreeNode root) {
		//先统计树的节点个数
		int count = getCount(root);
		//初始化数组
		int[][] result = new int[3][count];
		//进行计算 出结果
		threeOrder(root, result);
		return result;
	}

	//统计树的节点个数
	public int getCount(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getCount(root.left) + getCount(root.right) + 1;
	}

	//进行前中后序遍历
	public void threeOrder(TreeNode root, int[][] result) {
		if (root == null) {
			return;
		}
		result[0][pre++] = root.val;
		threeOrder(root.left, result);
		result[1][in++] = root.val;
		threeOrder(root.right, result);
		result[2][sub++] = root.val;
	}

	public int[][] threeOrders1(TreeNode root) {
		int len = getCount1(root);
		int[][] res = new int[3][len];
		dfs(root, res);
		return res;
	}

	public int getCount1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getCount(root.left) + getCount(root.right) + 1;
	}

	int i = 0, j = 0, k = 0;

	public void dfs(TreeNode root, int[][] res) {
		if (root == null) {
			return;
		}
		res[0][i++] = root.val;
		dfs(root.left, res);
		res[1][j++] = root.val;
		dfs(root.right, res);
		res[2][k++] = root.val;
	}
}
