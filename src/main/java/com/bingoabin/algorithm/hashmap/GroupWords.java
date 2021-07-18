package com.bingoabin.algorithm.hashmap;

import java.util.*;

/**
 * @author xubin34
 * @date 2021/7/18 12:48 下午
 */
public class GroupWords {
	//LeetCode 面试题 10.02. 变位词组
	//实例：输入: ["eat", "tea", "tan", "ate", "nat", "bat"],输出:
	// [
	//   ["ate","eat","tea"],
	//   ["nat","tan"],
	//   ["bat"]
	// ]
	//分析：将所有的变位单词放到一组中，进行输出
	//思路：方式一：模拟+排序：创建HashMap<String,List<String>>中间，主要确定key的唯一，可以通过char[] 排序，然后装成string() 然后就可以确定唯一
	//     方式二：模拟+计数：创建HashMap<String,List<String>>中间，主要确定key的唯一，可以通过int[] 计数，然后转成string + "_",然后判断唯一，然后装成string() 然后就可以确定唯一
	public static void main(String[] args) {
		String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(str));
		System.out.println(groupAnagrams1(str));
	}

	//方式一：模拟+排序：创建HashMap<String,List<String>>中间，主要确定key的唯一，可以通过char[] 排序，然后装成string() 然后就可以确定唯一
	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			char[] strchar = str.toCharArray();
			Arrays.sort(strchar);
			String key = new String(strchar);
			//String key = String.valueOf(strchar);
			List<String> list = map.getOrDefault(key, new ArrayList<String>());
			list.add(str);
			map.put(key, list);
		}
		for (String key : map.keySet()) {
			res.add(map.get(key));
		}
		return res;
	}

	//方式二：模拟+计数：创建HashMap<String,List<String>>中间，主要确定key的唯一，可以通过int[] 计数，然后转成string + "_",然后判断唯一，然后装成string() 然后就可以确定唯一
	public static List<List<String>> groupAnagrams1(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			int[] ants = new int[26];
			for (char ch : str.toCharArray()) ants[ch - 'a']++;
			StringBuilder buff = new StringBuilder();
			for (int ant : ants) buff.append(ant + "_");
			String key = buff.toString();
			List<String> list = map.getOrDefault(key, new ArrayList<String>());
			list.add(str);
			map.put(key, list);
		}
		for (String key : map.keySet()) {
			res.add(map.get(key));
		}
		return res;
	}
}
