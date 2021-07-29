package com.bingoabin.algorithm.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/29 11:01 上午
 */
public class ZhiFindWays {
	//LeetCode 1104. 二叉树寻路
	//样例：输入：lavel = 14  返回：[1,3,4,14]
	//分析：给定一个二叉树，每个节点都有两个子节点，按下图按"之"字形标记，奇数顺序，偶数逆序，给一个节点编号，然后返回到该节点的路径
	//思路：从末尾向上计算，先创建结果集，将最后一个值加入到list中，然后上一层的结果就是 3*(Math.pow(2,--level) - label/2 - 1),最后加上1
	//     然后翻转结果集即可
	//                1
	//         3            2
	//      4     5      6    7
	//   15 14  13 12  11 10 9 8
	public static void main(String[] args) {
		TreeNode node15 = new TreeNode(15);
		TreeNode node14 = new TreeNode(14);
		TreeNode node13 = new TreeNode(13);
		TreeNode node12 = new TreeNode(12);
		TreeNode node11 = new TreeNode(11);
		TreeNode node10 = new TreeNode(10);
		TreeNode node9 = new TreeNode(9);
		TreeNode node8 = new TreeNode(8);

		TreeNode node4 = new TreeNode(4, node15, node14);
		TreeNode node5 = new TreeNode(5, node13, node12);
		TreeNode node6 = new TreeNode(6, node11, node10);
		TreeNode node7 = new TreeNode(7, node9, node8);

		TreeNode node3 = new TreeNode(3, node4, node5);
		TreeNode node2 = new TreeNode(2, node6, node7);
		TreeNode node1 = new TreeNode(1, node3, node2);
		System.out.println(pathInZigZagTree(14));
	}

	//思路：从末尾向上计算，先创建结果集，将最后一个值加入到list中，然后上一层的结果就是 3*(Math.pow(2,--level) - label/2 - 1),最后加上1
	//     然后翻转结果集即可
	public static List<Integer> pathInZigZagTree(int label) {
		ArrayList<Integer> integers = new ArrayList<>();//0.初始化存放结果的变量
		int level = (int) (Math.log(label) / Math.log(2));//2.计算label所在的层  Math.log表示求e为底 label的对数
		while (label > 1) {//5.循环直到遇到特殊情况1
			integers.add(label);//3.将label的结果添加到数组中
			label = (int) (3 * Math.pow(2, --level) - label / 2 - 1);//4.计算下一个label的值
		}
		integers.add(1);//6.添加特殊情况 1
		Collections.reverse(integers); //7.翻转数组
		return integers;//1.返回结果
	}

	@Test
	public void test() {
		System.out.println((int) (Math.log(14) / Math.log(2))); //return 3
	}
}
