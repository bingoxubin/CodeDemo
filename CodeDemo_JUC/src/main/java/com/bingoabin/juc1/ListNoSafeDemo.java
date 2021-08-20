package com.bingoabin.juc1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ListNoSafeDemo {
	public static void main(String[] args) {
		//listDemo();
		//setDemo();
		mapDemo();
	}

	private static void mapDemo() {
		//Map<String, String> map = new HashMap<>();
		Map<String, String> map = new ConcurrentHashMap<>();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));

				System.out.println(Thread.currentThread().getName() + "\t" + map);
			}, String.valueOf(i)).start();

		}
	}

	private static void setDemo() {
		//HashSet<String> set = new HashSet<>();
		Set<String> set = new CopyOnWriteArraySet<>();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 8));

				System.out.println(Thread.currentThread().getName() + "\t" + set);
			}, String.valueOf(i)).start();

		}
	}

	private static void listDemo() {
		//List<String> list = new ArrayList<>();
		List<String> list = new CopyOnWriteArrayList<>();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));

				System.out.println(Thread.currentThread().getName() + "\t" + list);
			}, String.valueOf(i)).start();

		}
	}
}
