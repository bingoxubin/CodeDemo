package com.bingoabin.javase.commonclass;

/**
 * @author xubin03
 * @date 2021/5/19 8:35 下午
 */
public class Person implements Comparable<Person> {
	public int age;
	public String name;

	public Person() {
	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public Person(String name, int age) {
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Person o) {
		return this.age - o.age;
	}

	public void show() {
		System.out.println(this.name + " " + this.age);
	}
}