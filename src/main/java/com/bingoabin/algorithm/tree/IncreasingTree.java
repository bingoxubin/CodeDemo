package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/24 8:50
 */
public class IncreasingTree {
	//LeetCode 897. 递增顺序查找树
	//样例：输入 [5,3,6,2,4,null,8,1,null,null,null,7,9] 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
	//分析：将二叉树变成递增树
	//思路：
	//       5
	//       / \
	//     3    6
	//    / \    \
	//   2   4    8
	//  /        / \
	// 1        7   9
	// 转化为：
	// 1
	//   \
	//    2
	//     \
	//      3
	//       \
	//        4
	//         \
	//          5
	//           \
	//            6
	//             \
	//              7
	//               \
	//                8
	//                 \
	//                  9
	public static void main(String[] args) {
		TreeNode node7 = new TreeNode(1);
		TreeNode node5 = new TreeNode(4);
		TreeNode node8 = new TreeNode(7);
		TreeNode node9 = new TreeNode(9);
		TreeNode node4 = new TreeNode(2, node7, null);
		TreeNode node6 = new TreeNode(8, node8, node9);
		TreeNode node2 = new TreeNode(3, node4, node5);
		TreeNode node3 = new TreeNode(6, null, node6);
		TreeNode node1 = new TreeNode(5, node2, node3);
		//验证结果
		IncreasingTree binarySumTree = new IncreasingTree();
		TreeNode res = binarySumTree.increasingBST(node1);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.right;
		}
	}

	TreeNode res;

	public TreeNode increasingBST(TreeNode root) {
		TreeNode dummy = new TreeNode(-1);
		res = dummy;
		dfs(root);
		return dummy.right;
	}

	public void dfs(TreeNode root) {
		if (root == null) return;
		dfs(root.left);
		res.right = root;
		root.left = null;   //注意是root
		res = root;
		dfs(root.right);
	}
}
