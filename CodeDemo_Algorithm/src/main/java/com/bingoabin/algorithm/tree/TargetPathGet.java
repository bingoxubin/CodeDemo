package com.bingoabin.algorithm.tree;

import java.util.*;

/**
 * @author bingoabin
 * @date 2021/6/18 20:17
 */
public class TargetPathGet {
	//NC8 二叉树根节点到叶子节点和为指定值的路径
	//样例：输入 [3,5,1,6,2,0,8,#,#,7,4],14 输出：3 5 2 4
	//分析：计算值为指定和的路径
	//思路：方式一：通过迭代方式
	//     方式二：通过递归方式
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

		System.out.println(pathSum1(node3, 14));
		System.out.println(pathSum2(node3, 14));
	}

	//方式一；迭代的方式
	static ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	static HashMap<TreeNode,TreeNode> map = new HashMap<TreeNode,TreeNode>();
	public static ArrayList<ArrayList<Integer>> pathSum1(TreeNode root, int sum) {
		if(root == null) return res;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		Deque<Integer> value = new LinkedList<Integer>();
		queue.offer(root);
		value.offer(root.val);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			int num = value.poll();
			if(node.left == null && node.right == null){
				if(num == sum){
					getPath(node);
				}
			}else{
				if(node.left != null){
					map.put(node.left,node);
					queue.offer(node.left);
					value.offer(num + node.left.val);
				}
				if(node.right != null){
					map.put(node.right,node);
					queue.offer(node.right);
					value.offer(num + node.right.val);
				}
			}
		}
		return res;
	}

	public static void getPath(TreeNode root){
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(root != null){
			ans.add(root.val);
			root = map.get(root);
		}
		Collections.reverse(ans);
		res.add(ans);
	}

	//方式二：递归的方式
	static ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	static ArrayList<Integer> subresult = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
		if(root == null) return result;
		subresult.add(root.val);
		sum = sum - root.val;
		if(sum == 0 && root.left == null && root.right == null){
			result.add(new ArrayList<>(subresult)); //千万注意要new一个
		}
		pathSum2(root.left,sum);
		pathSum2(root.right,sum);
		subresult.remove(subresult.size() - 1);
		return result;
	}
}
