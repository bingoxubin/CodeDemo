package com.bingoabin.algorithm.tree;

/**
 * @author bingoabin
 * @date 2021/6/26 10:51
 */
public class ArrayToAVL {
	//NC11 有序数组转化为二叉平衡树
	//样例：输入 [1,0,1,0,1,0,1] 输出：平衡二叉树
	//分析：将有序数组转化为AVL树
	//思路：通过递归方式
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		ArrayToAVL arrayToAVL = new ArrayToAVL();
		System.out.println(arrayToAVL.sortedArrayToBST(arr).val);
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		return helper(nums, 0, nums.length - 1);
	}

	public TreeNode helper(int[] arr, int left, int right) {
		if (left > right) return null;
		int mid = (left + right) / 2 + (left + right) % 2;  //  不能单纯的用（left + right)/2 因为用例[1,3] 需要返回 {3,1},此时返回了{1,#,3}
		TreeNode root = new TreeNode(arr[mid]);
		root.left = helper(arr, left, mid - 1);
		root.right = helper(arr, mid + 1, right);
		return root;
	}
}
