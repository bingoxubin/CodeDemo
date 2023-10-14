package com.bingoabin.test;

import java.io.File;
import java.util.HashMap;

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
		// long l = 2147483648l;
		// System.out.println(Integer.parseInt(String.valueOf(l)));

		System.out.println(4&7);
	}

	@org.junit.Test
	public void test(){
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("当前目录是: " + currentDirectory);
		//F:\project\CodeDemo\CodeDemo_ZTest
		File file = new File("\\tmp\\hive\\bingo");
		System.out.println(file.isDirectory());

		File file1 = new File("\\test\\test1\\test20232023");
		file1.mkdirs();
		System.out.println(file1.isDirectory());
	}
}