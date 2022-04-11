package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/24 0:05
 */
public class BinarySumTree {
	//LeetCode 1022. 从根到叶的二进制数之和
	//样例：输入 [1,0,1,0,1,0,1] 输出：22
	//分析：计算从根到叶子节点的所有路径和,为二进制
	//思路：方式一：通过迭代方式
	//              1
	//         0         1
	//      0     1    0    1
	// 结果为 4 + 5 + 6 + 7 = 22
	public static void main(String[] args) {
		TreeNode node4 = new TreeNode(0);
		TreeNode node5 = new TreeNode(1);
		TreeNode node6 = new TreeNode(0);
		TreeNode node7 = new TreeNode(1);
		TreeNode node2 = new TreeNode(0, node4, node5);
		TreeNode node3 = new TreeNode(1, node6, node7);
		TreeNode node1 = new TreeNode(1, node2, node3);
		BinarySumTree binarySumTree = new BinarySumTree();
		System.out.println(binarySumTree.sumRootToLeaf(node1));
	}

	int res = 0;

	public int sumRootToLeaf(TreeNode root) {
		dfs(root, 0);
		return res;
	}

	private void dfs(TreeNode root, int sum) {
		if (root == null) return;
		if (root.left == null && root.right == null) {
			res += 2 * sum + root.val;
			return;
		}
		dfs(root.left, 2 * sum + root.val);
		dfs(root.right, 2 * sum + root.val);
	}
}
