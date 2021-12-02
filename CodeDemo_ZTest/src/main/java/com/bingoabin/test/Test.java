package com.bingoabin.test;

import java.util.HashMap;

/**
 * @author xubin34
 * @date 2021/8/3 5:43 下午
 * @copyright sankuai.com
 */
public class Test {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 111);
		map.put(2, 222);
		map.put(3, 333);
		map.put(4, 444);
		map.put(5, 555);
		map.put(6, 6);

		map.keySet().forEach(e -> {
			System.out.println(e);
			// System.out.println(map.get(e));
		});

		map.entrySet().forEach(e -> {
			System.out.println(e.getKey());
			System.out.println(e.getClass());
		});

		// List<Long> list = null;
		// HashSet<Long> longHashSet = new HashSet<Long>(list);
		System.out.println(Integer.MAX_VALUE);

		//超过2147483647 就报错了
		long l = 2147483648l;
		System.out.println(Integer.parseInt(String.valueOf(l)));
	}
}