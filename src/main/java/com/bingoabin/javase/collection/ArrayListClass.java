package com.bingoabin.javase.collection;

import java.util.*;

/**
 * @author bingoabin
 * @date 2021/7/3 10:42
 */
public class ArrayListClass {
	//初始化arraylist的四种方法
	public static void main(String[] args) {
		//方式一：Arrays.asList
		ArrayList<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

		//方式二：生成匿名内部内进行初始化
		ArrayList<Integer> list2 = new ArrayList<Integer>() {{
			add(1);
			add(2);
			add(3);
		}};

		//方式三：常规方式
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		//或者
		List list = Arrays.asList(1, 2, 3, 4, 5);
		list3.addAll(list);

		//方式四：Collections.ncopies
		ArrayList<Integer> obj = new ArrayList<Integer>(Collections.nCopies(3, 1));//把element复制count次填入ArrayList中
	}
}
