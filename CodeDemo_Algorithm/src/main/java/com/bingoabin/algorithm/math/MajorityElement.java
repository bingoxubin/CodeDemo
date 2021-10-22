package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/10/22 10:53 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MajorityElement {
	//Leetcode 229. 求众数 II
	//示例：输入：[1,1,1,3,3,2,2,2]  输出：[1,2]
	//分析：给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
	//思路：通过hashmap计数  然后统计
	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 3, 3, 2, 2};
		MajorityElement majorityElement = new MajorityElement();
		System.out.println(majorityElement.majorityElement(arr));
	}

	public List<Integer> majorityElement(int[] nums) {
		int len = nums.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		ArrayList<Integer> res = new ArrayList<>();
		for (Integer key : map.keySet()) {
			if (map.get(key) > len / 3) {
				res.add(key);
			}
		}
		return res;
	}
}
