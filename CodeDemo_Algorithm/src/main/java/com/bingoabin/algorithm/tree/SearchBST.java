package com.bingoabin.algorithm.tree;

/**
 * @Author: xubin34
 * @Date: 2021/11/26 1:29 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SearchBST {
	//Leetcode 700. 二叉搜索树中的搜索
	//示例：例如，
	// 给定二叉搜索树:
	//
	//         4
	//        / \
	//       2   7
	//      / \
	//     1   3
	// 和值: 2
	// 你应该返回如下子树:
	//       2
	//      / \
	//     1   3
	// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
	//分析：给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
	//思路：
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2, node1, node3);
		TreeNode node7 = new TreeNode(7);
		TreeNode node4 = new TreeNode(4, node2, node7);

		SearchBST searchBST = new SearchBST();
		System.out.println(searchBST.searchBST1(node4, 2).val);
		System.out.println(searchBST.searchBST2(node4, 2).val);
		System.out.println(searchBST.searchBST3(node4, 2).val);
		System.out.println(searchBST.searchBST4(node4, 2).val);
		System.out.println(searchBST.searchBST1(node4, 5));
		System.out.println(searchBST.searchBST2(node4, 5));
		System.out.println(searchBST.searchBST3(node4, 5));
		System.out.println(searchBST.searchBST4(node4, 5));
	}

	//方式一：广度优先
	public TreeNode searchBST1(TreeNode root, int val) {
		while (root != null) {
			if (root.val == val) {
				return root;
			} else if (root.val > val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return null;
	}

	//方式二：深度优先
	public TreeNode searchBST2(TreeNode root, int val) {
		if (root == null || root.val == val) return root;
		return root.val > val ? searchBST2(root.left, val) : searchBST2(root.right, val);
	}

	//自己实现一遍
	public TreeNode searchBST3(TreeNode root, int val) {
		if (root == null || root.val == val) return root;
		return root.val > val ? searchBST3(root.left, val) : searchBST3(root.right, val);
	}

	//自己实现一遍
	public TreeNode searchBST4(TreeNode root, int val) {
		while (root != null) {
			if (root.val == val) {
				return root;
			} else if (root.val > val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return null;
	}
}
