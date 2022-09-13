package com.bingoabin.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author bingoabin
 * @date 2022/9/13 15:30
 * @Description: 快速失败
 */
public class FailFastTest {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getKey() == 2) {
				iterator.remove();
			}
		}
		System.out.println(map.size());
		for (Integer key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
	}

	public void test1() {
		HashSet<Integer> set = new HashSet<>();
		set.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next % 2 == 0) {
				iterator.remove();
			}
		}

		// for (Integer num : set) {
		// 	if (num % 2 == 0) {
		// 		set.remove(num);
		// 	}
		// }
		System.out.println(set.size());
	}

	//copyonwrite
	@Test
	public void test2() {
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next == 5) {
				list.add(6);
			}
		}
		System.out.println(list);
	}

	@Test
	public void test3() {
		Map<MyClass, String> map = new HashMap<MyClass, String>();
		MyClass my1 = new MyClass();
		MyClass my2 = new MyClass();

		map.put(my1, "my1");
		map.put(my2, "my2");

		my1 = null;
		System.gc();
		System.out.println("第1步" + map);

		my2 = null;
		System.gc();
		System.out.println("第2步" + map);
		System.out.println(map.size());
		map.clear();
		System.gc();
		System.out.println("第3步" + map);
		System.out.println(map.size());
	}
}

class MyClass {
	private String msg = super.toString() + " 对象还在呢";

	@Override
	public String toString() {
		return msg;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println(super.toString() + " 对象被释放了");
	}
}