package com.bingoabin.collection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author xubin03
 * @date 2021/5/19 8:48 下午
 */
public class MapClass {
	@Test
	public void test5() {
		Map map = new HashMap();
		map.put("AA", 123);
		map.put(45, 1234);
		map.put("BB", 56);

		//遍历所有的key集：keySet()
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();
		//遍历所有的value集：values()
		Collection values = map.values();
		for (Object obj : values) {
			System.out.println(obj);
		}
		System.out.println();
		//遍历所有的key-value
		//方式一：entrySet()
		Set entrySet = map.entrySet();
		Iterator iterator1 = entrySet.iterator();
		while (iterator1.hasNext()) {
			Object obj = iterator1.next();
			//entrySet集合中的元素都是entry
			Map.Entry entry = (Map.Entry) obj;
			System.out.println(entry.getKey() + "---->" + entry.getValue());

		}
		System.out.println();
		//方式二：
		Set keySet = map.keySet();
		Iterator iterator2 = keySet.iterator();
		while (iterator2.hasNext()) {
			Object key = iterator2.next();
			Object value = map.get(key);
			System.out.println(key + "=====" + value);

		}

	}

	/**
	 * 元素查询的操作：
	 * Object get(Object key)：获取指定key对应的value
	 * boolean containsKey(Object key)：是否包含指定的key
	 * boolean containsValue(Object value)：是否包含指定的value
	 * int size()：返回map中key-value对的个数
	 * boolean isEmpty()：判断当前map是否为空
	 * boolean equals(Object obj)：判断当前map和参数对象obj是否相等
	 */
	@Test
	public void test4() {
		Map map = new HashMap();
		map.put("AA", 123);
		map.put(45, 123);
		map.put("BB", 56);
		// Object get(Object key)
		System.out.println(map.get(45));
		//containsKey(Object key)
		boolean isExist = map.containsKey("BB");
		System.out.println(isExist);

		isExist = map.containsValue(123);
		System.out.println(isExist);

		map.clear();

		System.out.println(map.isEmpty());

	}

	/*
	 添加、删除、修改操作：
	 Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
	 void putAll(Map m):将m中的所有key-value对存放到当前map中
	 Object remove(Object key)：移除指定key的key-value对，并返回value
	 void clear()：清空当前map中的所有数据
	 */
	@Test
	public void test3() {
		Map map = new HashMap();
		//添加
		map.put("AA", 123);
		map.put(45, 123);
		map.put("BB", 56);
		//修改
		map.put("AA", 87);

		System.out.println(map);

		Map map1 = new HashMap();
		map1.put("CC", 123);
		map1.put("DD", 123);

		map.putAll(map1);

		System.out.println(map);

		//remove(Object key)
		Object value = map.remove("CC");
		System.out.println(value);
		System.out.println(map);

		//clear()
		map.clear();//与map = null操作不同
		System.out.println(map.size());
		System.out.println(map);
	}

	@Test
	public void test2() {
		Map map = new HashMap();
		map = new LinkedHashMap();
		map.put(123, "AA");
		map.put(345, "BB");
		map.put(12, "CC");

		System.out.println(map);
	}

	@Test
	public void test1() {
		Map map = new HashMap();
		// map = new Hashtable();
		map.put(null, 123);
	}

	//向TreeMap中添加key-value，要求key必须是由同一个类创建的对象
	//因为要按照key进行排序：自然排序 、定制排序
	//自然排序
	@Test
	public void test6() {
		TreeMap map = new TreeMap();
		User u1 = new User("Tom", 23);
		User u2 = new User("Jerry", 32);
		User u3 = new User("Jack", 20);
		User u4 = new User("Rose", 18);

		map.put(u1, 98);
		map.put(u2, 89);
		map.put(u3, 76);
		map.put(u4, 100);

		Set entrySet = map.entrySet();
		Iterator iterator1 = entrySet.iterator();
		while (iterator1.hasNext()) {
			Object obj = iterator1.next();
			Map.Entry entry = (Map.Entry) obj;
			System.out.println(entry.getKey() + "---->" + entry.getValue());

		}
	}

	//定制排序
	@Test
	public void test7() {
		TreeMap map = new TreeMap(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof User && o2 instanceof User) {
					User u1 = (User) o1;
					User u2 = (User) o2;
					return Integer.compare(u1.getAge(), u2.getAge());
				}
				throw new RuntimeException("输入的类型不匹配！");
			}
		});
		User u1 = new User("Tom", 23);
		User u2 = new User("Jerry", 32);
		User u3 = new User("Jack", 20);
		User u4 = new User("Rose", 18);

		map.put(u1, 98);
		map.put(u2, 89);
		map.put(u3, 76);
		map.put(u4, 100);

		Set entrySet = map.entrySet();
		Iterator iterator1 = entrySet.iterator();
		while (iterator1.hasNext()) {
			Object obj = iterator1.next();
			Map.Entry entry = (Map.Entry) obj;
			System.out.println(entry.getKey() + "---->" + entry.getValue());

		}
	}

	@Test
	public void test8() {
		FileInputStream fis = null;
		try {
			Properties pros = new Properties();

			fis = new FileInputStream("jdbc.properties");
			pros.load(fis);//加载流对应的文件

			String name = pros.getProperty("name");
			String password = pros.getProperty("password");

			System.out.println("name = " + name + ", password = " + password);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			Properties pros = new Properties();

			fis = new FileInputStream("jdbc.properties");
			pros.load(fis);//加载流对应的文件

			String name = pros.getProperty("name");
			String password = pros.getProperty("password");

			System.out.println("name = " + name + ", password = " + password);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}


