package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;

/**
 * @author bingoabin
 * @date 2021/7/7 12:31
 */
public class CountPairs {
	//LeetCode 1711. 大餐计数
	//示例： [1,1,1,3,3,3,7] 返回：15 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
	//分析：数组中选择任意两个数，求等于2的幂的次数
	//思路：方式一：遍历数组中的每一个数，找在map中是否包含加上该值是2的幂的数，如果存在那就加上map.get(val),否则加上map中（无法AC）
	//     方式二：遍历数组，里面套一层循环，是从2的幂最小值到最大值，如果map中包含，则加上，如果不包含，那么就map累加(可以AC）
	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 3, 3, 3, 7};
		System.out.println(countPairs1(arr));
		System.out.println(countPairs2(arr));
	}

	//方式一：遍历数组中的每一个数，找在map中是否包含加上该值是2的幂的数，如果存在那就加上map.get(val),否则加上map中
	//注意：复杂度为n平方，在平台上无法AC
	public static int countPairs1(int[] ds) {
		int mod = 1000000007;
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < ds.length; i++) {
			for (int val : map.keySet()) {
				if (check(val + ds[i])) {
					res += map.get(val);
				}
			}
			map.put(ds[i], map.getOrDefault(ds[i], 0) + 1);
		}
		return (int) (res % mod);
	}

	public static boolean check(long x) {
		if (x > 0 && (x & (x - 1)) == 0) {
			return true;
		}
		return false;
	}

	//方式二：遍历数组，里面套一层循环，是从2的幂最小值到最大值，如果map中包含，则加上，如果不包含，那么就map累加
	//减少了时间负责度，log(N)  可以AC
	public static int countPairs2(int[] ds) {
		int mod = 1000000007;
		int res = 0;
		int max = 1 << 22;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < ds.length; i++) {
			for (int j = 1; j < max; j <<= 1) {
				if (map.containsKey(j - ds[i])) {
					res += map.get(j - ds[i]);
					if (res > mod) res -= mod;
				}
			}
			map.put(ds[i], map.getOrDefault(ds[i], 0) + 1);
		}
		return res;
	}
}
