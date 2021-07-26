package com.bingoabin.algorithm.greedy;

import java.util.*;

/**
 * @author xubin34
 * @date 2021/7/26 10:22 上午
 */
public class MinOperations {
	//LeetCode 1713. 得到子序列的最少操作次数
	//示例：输入：target = [5,1,3], arr = [9,4,2,3,4]   输出：2
	//分析：给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
	//     一次将target中取一个插入到arr中，返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
	//思路：
	public static void main(String[] args) {
		int[] target = {5, 1, 3};
		int[] arr = {9, 4, 2, 3, 4};
		System.out.println(minOperations(target, arr));
	}

	// 先转换为求最长公共子序列(LCS)
	// 再转换为求最长上升子序列(LIS)
	// 最后利用贪心算法求LIS
	public static int minOperations(int[] target, int[] arr) {
		Map<Integer, Integer> map = new HashMap();
		int n = target.length, m = arr.length;
		//步骤1. 映射target的下标
		for (int i = 0; i < n; i++) map.put(target[i], i + 1);

		//步骤2. 把arr的数字标记上在target上的下标
		int[] arr2 = new int[m + 1];
		for (int i = 0; i < m; i++) {
			if (map.containsKey(arr[i])) arr2[i + 1] = map.get(arr[i]); //这里下标从1开始
		}

		//步骤3. 贪心法求最长上升子序列
		List<Integer> list = new ArrayList();
		for (int i = 1; i <= m; i++) {
			if (arr2[i] == 0) continue;   //如果arr上的数字在target上没出现过
			if (list.isEmpty() || arr2[i] > list.get(list.size() - 1)) {
				list.add(arr2[i]);
			} else {
				int l = 0, r = list.size() - 1;
				while (l < r) {
					int mid = l + r >> 1;
					if (list.get(mid) >= arr2[i]) r = mid;
					else l = mid + 1;
				}
				list.set(l, arr2[i]);
			}
		}
		return n - list.size();
	}

	// 完成此题前，建议先完成300. 最长递增子序列 https://leetcode-cn.com/problems/longest-increasing-subsequence/
	// 思路：把arr中包含target中的整数及其在target中的索引按顺序缓存下来一个数组，然后计算这个数组的最长递增子序列即可。
	public static int minOperations1(int[] target, int[] arr) {
		// 第一步，把target中的所有值对应的索引存到哈希表中
		HashMap<Integer, Integer> hashMap = new HashMap();
		for (int i = 0; i < target.length; i++) {
			hashMap.put(target[i], i);
		}
		// 第二步，把arr中在target数组存在的数字的索引按顺序排列到新的数组中
		int index = 0;
		int[] indexArray = new int[arr.length];
		Arrays.fill(indexArray, -1); // 初始填充-1
		for (int i = 0; i < arr.length; i++) {
			if (hashMap.containsKey(arr[i])) {
				indexArray[index++] = hashMap.get(arr[i]);
			}
		}
		// 如果都为-1，代码arr中没有target中的整数，返回target长度
		if (indexArray[0] == -1) return target.length;
		// 第三步，计算indexArray中的最长递增子序列，这里直接套用 300. 最长递增子序列 题目的官方解法；
		int num = lengthOfLIS(indexArray);

		return target.length - num;
	}

	private static int lengthOfLIS(int[] nums) {
		int len = 1, n = nums.length;
		if (n == 0) {
			return 0;
		}
		int[] d = new int[n + 1];
		d[len] = nums[0];
		for (int i = 1; i < n; ++i) {
			if (nums[i] > d[len]) {
				d[++len] = nums[i];
			} else {
				int l = 1, r = len, pos = 0;
				while (l <= r) {
					int mid = (l + r) >> 1;
					if (d[mid] < nums[i]) {
						pos = mid;
						l = mid + 1;
					} else {
						r = mid - 1;
					}
				}
				d[pos + 1] = nums[i];
			}
		}
		return len;
	}
}
