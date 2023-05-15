package com.bingoabin.acwing._01basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2023/5/15 9:20
 * @Description:
 */
public class _15LongestNoRepeatSubsequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(getMax(arr));
	}

	public static int getMax(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = 0;
		int start = 0;
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				start = Math.max(start, map.get(arr[i]) + 1);
			}
			map.put(arr[i], i);
			res = Math.max(res, i - start + 1);
		}
		return res;
	}
}
