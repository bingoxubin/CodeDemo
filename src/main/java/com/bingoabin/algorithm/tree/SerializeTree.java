package com.bingoabin.algorithm.tree;

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
	}

	//方式一：迭代方式
	public String serialize1(TreeNode root) {
		return null;
	}

	public TreeNode deserialize1(String data) {
		return null;
	}

	//方式二：递归方式
	public String serialize2(TreeNode root) {
		return null;
	}

	public TreeNode deserialize2(String data) {
		return null;
	}
}
