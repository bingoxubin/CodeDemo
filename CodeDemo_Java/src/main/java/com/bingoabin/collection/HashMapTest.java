package com.bingoabin.collection;

import java.util.HashMap;

/**
 * @author xubin34
 * @date 2021/7/29 9:17 下午
 */
public class HashMapTest {
	public static void main(String[] args) {
		//判断一个hashmap当中的key是hashmap的时候，这是一样的么？进行验证
		HashMap<HashMap<String, Object>, String> map = new HashMap<>();

		//定义一个hashmap map1
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("1", 1);
		map1.put("2", 2);
		map1.put("3", 3);

		//定义一个hashmap map2
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("3", 3);
		map2.put("2", 2);
		map2.put("1", 1);

		//实际上map1 map2 两个的元素都是一样的
		//并且放入两个不同的值 相同的key
		map.put(map1, "1");
		map.put(map2, "2");

		//验证map中的key有多少个  如果是1个  那就是相同的
		System.out.println(map.size());  //1
		System.out.println(map.get(map1));//2
		System.out.println(map.get(map2));//2
	}
}
