package com.bingoabin.algorithm.trie;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xubin03
 * @date 2021/5/24 11:12 上午
 */
public class MaxXORInArray {
	//LeetCode 1707. 与数组中元素的最大异或值    https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
	//样例：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]  返回 [3,3,7]
	//分析：给一个数组，以及queries，按照queries中的数组来进行求值，queries中2个数组表示，原来的数组中与qutries[0]可以得到的最大异或值，但是有个条件，原数组的参与异或的数不能超过queries[1]
	//思路：用前缀树，TrieTree，先将数组中的数据按照二进制从首位到末尾插入到前缀树中（0，1)树，再遍历原数组，遍历一个，从TrieTree树中找到对应的最大值，记录最大值结果比较
	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 3, 4};
		int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
		MaxXORInArray mxoria = new MaxXORInArray();
		int[] res = mxoria.maximizeXor(nums, queries);
		System.out.println(Arrays.toString(res));
	}

	public int[] maximizeXor(int[] nums, int[][] queries) {
		Arrays.sort(nums);
		int[][] helper = new int[queries.length][3];
		for (int i = 0; i < queries.length; i++) {
			helper[i][0] = queries[i][0];
			helper[i][1] = queries[i][1];
			helper[i][2] = i;
		}
		Arrays.sort(helper, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		TrieTree tt = new TrieTree();
		int[] res = new int[queries.length];
		int index = 0;
		for (int i = 0; i < queries.length; i++) {
			while (index < nums.length && nums[index] <= helper[i][1]) {
				tt.add(nums[index++]);
			}
			if (index == 0) {
				res[helper[i][2]] = -1;
				continue;
			} else {
				res[helper[i][2]] = tt.query(helper[i][0]);
			}
		}
		return res;
	}

	class TrieTree {
		TrieTree[] child;

		TrieTree() {
			child = new TrieTree[2];
		}

		public void add(int num) {
			TrieTree root = this;
			for (int i = 30; i >= 0; i--) {
				int tmp = (num >> i) & 1;
				if (root.child[tmp] == null) {
					root.child[tmp] = new TrieTree();
				}
				root = root.child[tmp];

			}
		}

		public int query(int num) {
			TrieTree root = this;
			int res = 0;
			for (int i = 30; i >= 0; i--) {
				int a = (num >> i) & 1;
				int b = 1 - a;
				if (root.child[b] == null) {
					res |= a << i;
					root = root.child[a];
				} else {
					res |= b << i;
					root = root.child[b];
				}
			}
			return res ^ num;
		}
	}
}

