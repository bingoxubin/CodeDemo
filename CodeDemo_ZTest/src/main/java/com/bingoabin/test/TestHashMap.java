package com.bingoabin.test;

import com.bingoabin.domain.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingoabin
 * @date 2022/5/24 20:36
 */
public class TestHashMap {
	public static void main(String[] args){
		Person person1 = new Person(1, "xb");
		Person person2 = new Person(2, "zhangsan");
		Person person3 = new Person(3, "lisi");
		Map<Person, Integer> map = new HashMap<>();

		map.put(person1,2);
		map.put(person2, 3);
		map.put(person3, 4);
		System.out.println(map.get(person1));
		person1.setId(2);
		System.out.println(map.get(person1));
	}
}
