package com.bingoabin.test;

import com.bingoabin.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: xubin34
 * @Date: 2021/11/18 3:39 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestEquals {
	public static void main(String[] args) {
		List<Person> list1 = new ArrayList<Person>();
		list1.add(new Person(1, "xb"));
		list1.add(new Person(2, "liling"));

		List<Person> list2 = new ArrayList<>();
		list2.add(new Person(2, "liling"));
		list2.add(new Person(1, "xb"));

		System.out.println(Objects.equals(list1, list2));
	}
}
