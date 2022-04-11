package com.bingoabin.algorithm.hashmap;

import java.util.*;

/**
 * @author xubin03
 * @date 2021/5/20 11:56 上午
 */
public class KthFrequencyWord {
	//692. 前K个高频单词  https://leetcode-cn.com/problems/top-k-frequent-words/
	//样例：["i", "love", "leetcode", "i", "love", "coding"], k = 2  返回：["i", "love"]
	//分析：一个字符串数组，其中都是单词，求出前k个高频次的单词
	//思路：先用hashmap统计出单词的个数，然后按照key的string进行value频次的定制排序
	public static void main(String[] args) {
		String[] str = {"i", "love", "leetcode", "i", "love", "coding"};
		System.out.println(topKFrequent(str, 2));
	}

	public static List<String> topKFrequent(String[] words, int k) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//统计次数
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		//用优先队列进行删选,小顶堆
		PriorityQueue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (map.get(s1).equals(map.get(s2))) {
					return s2.compareTo(s1);
				}
				return map.get(s1) - map.get(s2);
			}
		});
		//将string放入优先队列，小顶堆中，如果队列中超过了k个元素，弹出即可
		for (String key : map.keySet()) {
			queue.add(key);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		//将最后队列中的结果放入到list结果集中
		List<String> res = new ArrayList<String>();
		while (queue.size() != 0) {
			res.add(queue.poll());
		}
		//逆序排一下返回
		Collections.reverse(res);
		return res;
	}
}
