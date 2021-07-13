package com.bingoabin.algorithm.string;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author xubin03
 * @date 2021/6/7 6:49 下午
 */
public class MaxLenContinousString {
	//NC95 最长连续子序列
	//样例：输入[100,4,200,1,3,2] 输出：4
	//分析：无序数组arr，返回其中最长的连续序列的长度(要求值连续，位置可以不连续,例如 3,4,5,6为连续的自然数）
	//思路:方式一:无需排序，先把所有数据放到一个hashset中，然后遍历数组，如果set中包含数组中的值 - 1直接跳过，如果不包含，往上累加，求出最大长度然后比较，赋值
	//    方式二：先进行排序，然后从第一个位置开始遍历，如果这个值，比前面一直大1，那么长度++，如果相等，那么继续continue，如果不是的话，长度回到1
	public static void main(String[] args) {
		int[] arr = {100, 4, 200, 1, 3, 2};
		System.out.println(MLS1(arr));
		System.out.println(MLS2(arr));
	}

	public static int MLS1(int[] arr) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : arr) {
			set.add(num);
		}
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			if (!set.contains(num - 1)) {
				int count = 0;
				while (set.contains(num)) {
					count++;
					num++;
				}
				max = Math.max(max, count);
			}
		}
		return max;
	}

	public static int MLS2(int[] arr) {
		Arrays.sort(arr);
		int res = 1;
		int len = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) continue;
			if (arr[i] - arr[i - 1] == 1) {
				len++;
				res = Math.max(len, res);
			} else {
				len = 1;
			}
		}
		return res;
	}
}
