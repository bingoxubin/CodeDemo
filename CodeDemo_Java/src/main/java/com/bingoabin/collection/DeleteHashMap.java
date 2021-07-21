package com.bingoabin.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author xubin34
 * @date 2021/7/21 11:09 上午
 */
public class DeleteHashMap {
	public static void main(String[] args) {
		//删除hashmap中的元素  错误示范
		errorTest();
	}

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
