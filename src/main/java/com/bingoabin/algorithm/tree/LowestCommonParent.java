package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/16 14:03
 */
public class LowestCommonParent {
	//NC102 最近公共祖先
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],5,1 输出：3
	//分析：查找二叉树的最近公共祖先
	//思路：通过递归遍历
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

		System.out.println(lowestCommonAncestor(node3, 8, 0));
	}

	public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
		return commonAncestor(root, o1, o2).val;
	}

	//通过递归调用，后续遍历，如果root.val == o1 或者 o2 那么直接返回root,如果两个都在右边 返回右边，都在左边那么返回左边，在两遍  返回root
	public static TreeNode commonAncestor(TreeNode root, int o1, int o2) {
		if (root == null || root.val == o1 || root.val == o2) return root;
		TreeNode left = commonAncestor(root.left, o1, o2);
		TreeNode right = commonAncestor(root.right, o1, o2);
		if (left == null) return right;
		if (right == null) return left;
		return root;
	}
}
