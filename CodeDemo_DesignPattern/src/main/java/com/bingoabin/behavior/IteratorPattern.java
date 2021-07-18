package com.bingoabin.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author bingoabin
 * @date 2021/6/15 1:47
 */
public class IteratorPattern {
	//迭代器模式：是一种行为型设计模式，让你能再不暴露集合底层表现形式（列表，栈，树等）的情况下遍历集合中的所有的元素
	public static void main(String[] args) {
		//方式一：
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		Iterator iterator = list.iterator();

		//方式二：
		ArrayList<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
		Iterator iterator1 = list1.iterator();

		//遍历
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}

class Iterator1 {

}
