package com.bingoabin.algorithm.trie;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * @author xubin03
 * @date 2021/5/17 1:34 上午
 */
public class TwoMaxXORInArray {
	//Leetcode.421 数组中两个数的最大异或值  https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
	//样例：[3,10,5,25,2,8]  返回 5 XOR 25 = 28
	//分析：给一个数组，随机选取两个数，找出可以求得的最大的异或值
	//思路：用前缀树，TrieTree，先将数组中的数据按照二进制从首位到末尾插入到前缀树中（0，1)树，再遍历原数组，遍历一个，从TrieTree树中找到对应的最大值，记录最大值结果比较
	public static void main(String[] args) {
		int[] arr = {3, 10, 5, 25, 2, 8};
		int[] arr1 = {0, 1, 2, 3, 4};
		TwoMaxXORInArray twoMaxXORInArray = new TwoMaxXORInArray();
		System.out.println(twoMaxXORInArray.findMaximumXOR(arr1));
	}

	//最终方法，求出数组中，两个数的最大异或和
	public int findMaximumXOR(int[] nums) {
		TrieTree tt = new TrieTree();
		int max = 0;
		for (int num : nums) {
			tt.add(num);
			int value = tt.getVal(num);
			max = Math.max(max, num ^ value);
			System.out.println(num + " " + value + " " + max);
		}
		return max;
	}

	class TrieTree {
		TrieTree[] node;

		TrieTree() {
			node = new TrieTree[2];
		}

		//将一个数据添加到TrieTree树中
		public void add(int num) {
			TrieTree p = this;
			for (int i = 30; i >= 0; i--) {
				int temp = (num >> i) & 1;
				if (p.node[temp] == null) {
					p.node[temp] = new TrieTree();
				}
				p = p.node[temp];
			}
		}

		//从TrieTree树中，找出与num求得最大异或和的数
		public int getVal(int num) {
			TrieTree p = this;
			int res = 0;
			for (int i = 30; i >= 0; i--) {
				int a = (num >> i) & 1;
				int b = 1 - a;
				//找跟a相反的在TrieTree中有没有，没有的话，继续遍历a,有的话从b开始找
				if (p.node[b] == null) {
					res |= a << i;
					p = p.node[a];
				} else {
					res |= b << i;
					p = p.node[b];
				}
			}
			return res;
		}
	}
}
