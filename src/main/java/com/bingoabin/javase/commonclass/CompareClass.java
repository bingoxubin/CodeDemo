package com.bingoabin.javase.commonclass;

import java.util.*;

/**
 * @author xubin03
 * @date 2021/5/19 5:28 下午
 */
public class CompareClass {
	public static void main(String[] args) {
		//一、定制排序
		String[] arr = {"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		System.out.println(Arrays.toString(arr));

		//二、自然排序
		List<Person> list = new ArrayList<Person>();
		list.add(new Person(3, "nihao"));
		list.add(new Person(2, "hello"));
		list.add(new Person(1, "world"));
		list.add(new Person(4, "java"));
		//采用定制化，优先级比较高
		list.sort(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.age - o1.age;
			}
		});
		// list.sort(null);
		//或者
		Collections.sort(list);
		for (Person person : list) {
			System.out.println(person.age + " " + person.name);
		}
	}

	//Comparable接口的方式一旦一定，保证Comparable接口实现类的对象在任何位置都可以比较大小。
	//Comparator接口属于临时性的比较。
	//comparable接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
	//comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序
	//一般我们需要对一个集合使用自定义排序时，我们就要重写compareTo()方法或compare()方法，当我们需要对某一个集合实现两种排序方式，
	//      比如一个song对象中的歌名和歌手名分别采用一种排序方法的话，我们可以重写compareTo()方法和使用自制的Comparator方法或者
	//      以两个Comparator来实现歌名排序和歌星名排序，第二种代表我们只能使用两个参数版的 Collections.sort().
}
