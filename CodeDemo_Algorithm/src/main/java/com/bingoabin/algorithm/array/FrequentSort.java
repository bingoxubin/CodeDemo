package com.bingoabin.algorithm.array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author bingoabin
 * @date 2021/7/3 1:26
 */
public class FrequentSort {
	//LeetCode 451. 根据字符出现频率排序
	//示例："tree" 返回 "eert" 解释：'e'出现两次，'r'和't'都只出现一次。因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
	//分析：将一个字符串按照字符出现的频次排序，字符串长度最终不变，有多个答案，返回一个即可
	//思考：还是采用优先队列的方式，先统计字符串中的字符的出现次数，用hashmap统计，然后创建优先队列，将字符按照hashmap中的value进行逆序排序，然后将queue中的字母进行弹出，复写即可

	public static void main(String[] args) {
		String str = "tree";
		System.out.println(frequencySort(str));
	}

	//思考：还是采用优先队列的方式，先统计字符串中的字符的出现次数，用hashmap统计，然后创建优先队列，将字符按照hashmap中的value进行逆序排序，然后将queue中的字母进行弹出，复写即可
	public static String frequencySort(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] strs = s.toCharArray();
		for (char str : strs) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
			public int compare(Character c1, Character c2) {
				Integer i1 = map.get(c1);
				Integer i2 = map.get(c2);
				return i2.compareTo(i1);
			}
		});
		for (Character ch : map.keySet()) {
			queue.offer(ch);
		}
		StringBuffer res = new StringBuffer();
		while (!queue.isEmpty()) {
			char ch = queue.poll();
			int times = map.get(ch);
			for (int i = 0; i < times; i++) {
				res.append(ch);
			}
		}
		return res.toString();
	}
}
