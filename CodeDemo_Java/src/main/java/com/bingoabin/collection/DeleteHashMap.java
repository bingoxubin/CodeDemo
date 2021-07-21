package com.bingoabin.collection;

import java.util.*;

/**
 * @author xubin34
 * @date 2021/7/21 11:09 上午
 */
public class DeleteHashMap {
	public static void main(String[] args) {
		//删除hashmap中的元素  错误示范
		//errorTest();
		//删除hashmap中的元素  正确示范
		correctTest();
	}

	//删除元素  正确的方式
	public static void correctTest() {
		List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
		HashMap<String, Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 1);
		map.put("3", 1);
		map.put("4", 1);
		map.put("5", 1);
		map.put("6", 1);
		map.put("7", 1);

		//方式一：
		Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> next = iterator.next();
			if (list.contains(next.getKey())) {
				iterator.remove();
			}
		}

		//方式二：
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String next = iter.next();
			if(list.contains(next)){
				iter.remove();
			}
		}

		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
	}

	//删除元素   错误的方式
	public static void errorTest() {
		List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
		HashMap<String, Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 1);
		map.put("3", 1);
		map.put("4", 1);
		map.put("5", 1);
		map.put("6", 1);
		map.put("7", 1);

		for (String key : map.keySet()) {
			if (!list.contains(key)) {
				map.remove(key);
			}
		}

		for (String key : map.keySet()) {
			System.out.print(key + " ");
		}
	}
}
