package com.bingoabin.algorithm.hashmap;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/5/27 12:43 下午
 */
public class SumEqualsMaxLength {
	//NC125 未排序数组中累加和为给定值的最长子数组长度  https://www.nowcoder.com/practice/704c8388a82e42e58b7f5751ec943a11?tpId=117&&tqId=37794&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：1,-2,1,1,1]  0 返回：3
	//分析：给一个数组，求在这个数组中得到和为目标值的最长子数组长度
	//思路：方式一：先求出数组的前缀和，然后用双指针，2重循环，求出结果
	//     方式二：先求出数组的前缀和，然后用hashmap缓存中间结果，求出结果值
	public static void main(String[] args) {
		int[] arr = {1, -2, 1, 1, -1};
		System.out.println(maxlenEqualK(arr, 0));
		System.out.println(maxlenEqualK1(arr, 0));
	}

	//方式一：先求出数组的前缀和，然后用双指针，2重循环，求出结果,最外面一层循环表示长度，第二层循环表示能取到i的长度，j的范围，比如长度为4的时候 j可以取0 ，1
	//   1 -2 1 1 1
	// 0 1 -1 0 1 2
	public static int maxlenEqualK(int[] arr, int k) {
		int[] helper = new int[arr.length + 1];
		for (int i = 1; i <= arr.length; i++) helper[i] = arr[i - 1] + helper[i - 1];
		for (int i = arr.length; i > 0; i--) {
			for (int j = 0; i + j <= arr.length; j++) {
				if (helper[i + j] - helper[j] == k) {
					return i;
				}
			}
		}
		return 0;
	}

	//方式二：先求出数组的前缀和，然后遍历前缀和，如果map中不存在，那就放入map中，key value分别是前缀和和下标，如果map中包含当前值 - k ,那就得出结果，比较长度，然后用hashmap缓存中间结果，求出结果值
	public static int maxlenEqualK1(int[] arr, int k) {
		int[] helper = new int[arr.length + 1];
		for (int i = 1; i <= arr.length; i++) helper[i] = helper[i - 1] + arr[i - 1];
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < helper.length; i++) {
			if (map.containsKey(helper[i] - k)) {
				res = Math.max(res, i - map.get(helper[i] - k));
			}
			if (!map.containsKey(helper[i])) {
				map.put(helper[i], i);
			}
		}
		return res;
	}
}
