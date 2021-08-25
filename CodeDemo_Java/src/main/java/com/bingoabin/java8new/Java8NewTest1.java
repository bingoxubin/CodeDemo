package com.bingoabin.java8new;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/8/20 5:56 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Java8NewTest1 {
	public static void main(String[] args) {
		//测试：arraylist 的 foreach
		List<User> list = new ArrayList<>();
		User user1 = new User(1, "xubin1", 25, "beijing", "sankuai");
		User user2 = new User(2, "xubin2", 26, "beijing1", "sankuai1");
		User user3 = new User(4, "xubin5", 27, "beijing2", "sankuai2");
		list.add(user1);
		list.add(user2);
		list.add(user3);

		//foreach
		list.forEach(e -> {
			System.out.println(e.toString());
		});

		//测试：hashmap 的 computeIfAbsent  absent缺席的
		//computeIfAbsent:检查map中的key，如果发现key不存在或者对应的值是null，则调用function来产生一个值，放入map中，最后返回这个值
		//putIfAbsent：这个不是get()方法，是put的一个变种。检查map中的key，如果发现key不存在或者是null，将value设置进去，然后返回null；否则只返回map中的对应的值，不做其他操作
		//getOrDefault：检查map中的key，如果发现key不存在或者对应的值是null，则返回第二个参数的默认值。注意的是：这个默认值不会放入map中
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);

		//检查map中是否有4 3；4没有那么插入一个4，返回4    3有，返回一个3对应的value3
		Integer computeIfAbsent1 = map.computeIfAbsent(4, k -> 4);   //4
		Integer computeIfAbsent2 = map.computeIfAbsent(3, k -> 4);  //3
		System.out.println(computeIfAbsent1 + " " + computeIfAbsent2);

		//检查map中是否有5，4；5没有,那么插入一个5,返回5之前的值null；4有，不插入，返回之前的值4
		Integer putIfAbsent1 = map.putIfAbsent(5, 5);    //null
		Integer putIfAbsent2 = map.putIfAbsent(4, 5);   //4
		System.out.println(putIfAbsent1 + " " + putIfAbsent2);

		//检查map中是否有4，6；4有，返回4；6没有，返回6；但是不插入6
		Integer getordefault1 = map.getOrDefault(4, 4);    //4
		Integer getordefault2 = map.getOrDefault(6, 6);   //6
		System.out.println(getordefault1 + "=" + getordefault2);

		System.out.println(map.get(5) + " " + map.get(4) + " " + map.get(6));  //5  4  null
	}
}
