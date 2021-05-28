package com.bingoabin.javase.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author xubin03
 * @date 2021/5/19 8:37 下午
 */
public class SetClass {
	public static void main(String[] args) {

	}

	@Test
	public void test1() {
		Set set = new HashSet();
		set.add(456);
		set.add(123);
		set.add(123);
		set.add("AA");
		set.add("CC");
		set.add(new User("Tom", 12));
		set.add(new User("Tom", 12));
		set.add(129);
		set.add(null);

		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	//LinkedHashSet的使用
	//LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个
	//数据和后一个数据。
	//优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet
	@Test
	public void test2() {
		Set set = new LinkedHashSet();
		set.add(456);
		set.add(123);
		set.add(123);
		set.add("AA");
		set.add("CC");
		set.add(new User("Tom", 12));
		set.add(new User("Tom", 12));
		set.add(129);

		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	/*
   1.向TreeSet中添加的数据，要求是相同类的对象。
   2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）


   3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
   4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
	*/
	@Test
	public void test3() {
		TreeSet set = new TreeSet();

		//失败：不能添加不同类的对象
		//        set.add(123);
		//        set.add(456);
		//        set.add("AA");
		//        set.add(new User("Tom",12));

		//举例一：
		//        set.add(34);
		//        set.add(-34);
		//        set.add(43);
		//        set.add(11);
		//        set.add(8);

		//举例二：
		set.add(new User("Tom", 12));
		set.add(new User("Jerry", 32));
		set.add(new User("Jim", 2));
		set.add(new User("Mike", 65));
		set.add(new User("Jack", 33));
		set.add(new User("Jack", 56));

		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}

	@Test
	public void test4() {
		Comparator com = new Comparator() {
			//按照年龄从小到大排列
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof User && o2 instanceof User) {
					User u1 = (User) o1;
					User u2 = (User) o2;
					return Integer.compare(u1.getAge(), u2.getAge());
				} else {
					throw new RuntimeException("输入的数据类型不匹配");
				}
			}
		};

		TreeSet set = new TreeSet(com);
		set.add(new User("Tom", 12));
		set.add(new User("Jerry", 32));
		set.add(new User("Jim", 2));
		set.add(new User("Mike", 65));
		set.add(new User("Mary", 33));
		set.add(new User("Jack", 33));
		set.add(new User("Jack", 56));

		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}

class User implements Comparable {
	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		System.out.println("User equals()....");
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User user = (User) o;

		if (age != user.age) {
			return false;
		}
		return name != null ? name.equals(user.name) : user.name == null;
	}

	@Override
	public int hashCode() { //return name.hashCode() + age;
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + age;
		return result;
	}

	//按照姓名从大到小排列,年龄从小到大排列
	@Override
	public int compareTo(Object o) {
		if (o instanceof User) {
			User user = (User) o;
			//return -this.name.compareTo(user.name);
			int compare = -this.name.compareTo(user.name);
			if (compare != 0) {
				return compare;
			} else {
				return Integer.compare(this.age, user.age);
			}
		} else {
			throw new RuntimeException("输入的类型不匹配");
		}

	}
}
