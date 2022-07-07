package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/20 10:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindLHS {
	//Leetcode 594. 最长和谐子序列
	//示例：输入：nums = [1,3,2,2,5,2,3,7]
	//     输出：5
	//     解释：最长的和谐子序列是 [3,2,2,2,3]
	//分析：找数组中最长和谐子序列，就是最大值跟最小值相差不能超过1的子序列
	//思路：用hashmap计数  统计次数，然后遍历map中的key，判断是否存在key+1 然后统计最大值
	public static void main(String[] args) {
		int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
		FindLHS findLHS = new FindLHS();
		out.println(findLHS.findLHS1(nums));
		out.println(findLHS.findLHS2(nums));
		out.println(findLHS.findLHS(nums));
	}

	public int findLHS1(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		//统计数组中数字出现的次数
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		int res = 0;
		for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
			//遍历map中的key，如果存在key+1 那么统计最长的长度
			if (map.containsKey(kv.getKey() + 1)) {
				res = Math.max(res, map.get(kv.getKey()) + map.get(kv.getKey() + 1));
			}
		}
		return res;
	}

	public int findLHS2(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int res = 0;
		for (int num : nums) {
			//累加num出现的次数
			map.put(num, map.getOrDefault(num, 0) + 1);
			//如果存在num+1 统计次数
			if (map.containsKey(num + 1)) {
				res = Math.max(res, map.get(num) + map.get(num + 1));
			}
			//如果存在num-1 统计次数
			if (map.containsKey(num - 1)) {
				res = Math.max(res, map.get(num) + map.get(num - 1));
			}
		}
		return res;
	}

	public int findLHS(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = 0;
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for (int key : map.keySet()) {
			if (map.containsKey(key + 1)) {
				res = Math.max(res, map.get(key) + map.get(key + 1));
			}
		}
		return res;
	}
}
