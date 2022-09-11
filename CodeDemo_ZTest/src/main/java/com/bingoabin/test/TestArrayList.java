package com.bingoabin.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/9/11 10:09
 * @Description: 测试arraylist
 */
public class TestArrayList {
	public static void main(String[] args){
		// List<Integer> list = Arrays.asList(1, 2, 3);
		List<Integer> list = new ArrayList(Arrays.asList(1, 2, 3));
		System.out.println(list);
		list.add(4);
		System.out.println(list);

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(null, 1);
		map.put(1, null);
		System.out.println(map.get(1));
		System.out.println(map.get(null));
	}
}
