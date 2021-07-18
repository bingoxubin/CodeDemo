package com.bingoabin.algorithm.string;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/6/5 7:36 下午
 */
public class MaxLenSubString {
	//NC41 最长无重复子串
	//样例：输入[2,2,3,4,3] 输出：3
	//分析：给定一个数组arr，返回arr的最长无的重复子串的长度
	//思路：定义一个hashmap，如果前面不存在arr[i]这个key，那么放入key，value是i+1，表示i以前是重复的，i+1开始是不重复的，如果前面存在，取出来value，这个value跟一个变量index取一个最大值，长度为i-index + 1
	public static void main(String[] args) {
		int[] arr = {2, 2, 3, 4, 3};
		System.out.println(maxLength(arr));
	}

	public static int maxLength(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int res = 0;
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				index = Math.max(index, map.get(arr[i]));
			}
			map.put(arr[i], i + 1);
			res = Math.max(res, i - index + 1);
		}
		return res;
	}
}
