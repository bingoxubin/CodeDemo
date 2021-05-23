package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author xubin03
 * @date 2021/5/23 10:08 上午
 */
public class TimesTopK {
	//NC97 出现次数的topK问题  https://www.nowcoder.com/practice/fd711bdfa0e840b381d7e1b82183b3ee?tpId=117&tqId=37809&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：["a","b","c","b"],2 返回：[["b","2"],["a","1"]]
	//分析：给一个字符串数组，按照他们的出现次数，统计出topK
	//思路：先通过hashmap，统计出出现次数，然后创建优先队列，按照出现次数排序，如果次数相等，按照字母顺序排序，小根堆，将数据放入队列中，最后打印队列结果值
	public static void main(String[] args) {
		String[] res = {"a", "b", "c", "b"};
		String[][] val = topKstrings(res, 2);
		for (String[] key : val) {
			System.out.println(Arrays.toString(key));
		}
	}

	//先通过hashmap，统计出出现次数，然后创建优先队列，按照出现次数排序，如果次数相等，按照字母顺序排序，小根堆，将数据放入队列中，最后打印队列结果值
	public static String[][] topKstrings(String[] strings, int k) {
		HashMap<String, Integer> map = new HashMap<>();
		for (String string : strings) map.put(string, map.getOrDefault(string, 0) + 1);
		PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (map.get(o1).equals(map.get(o2))) {
					return o2.compareTo(o1);
				}
				return map.get(o1) - map.get(o2);
			}
		});
		for (String key : map.keySet()) {
			queue.add(key);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		String[][] res = new String[k][2];
		while (!queue.isEmpty()) {
			k = k - 1;
			String str = queue.poll();
			res[k][0] = str;
			res[k][1] = map.get(str) + "";
		}
		return res;
	}
}
