package com.bingoabin.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/4/18 16:45
 */
public class TestArray {
	public static void main(String[] args){
		List<Integer> list = new ArrayList<>();
		list.add(0, 1);
		System.out.println(list);
		list.add(0, 1);
		System.out.println(list);

		System.out.println("====================");
		List<Integer> res = new ArrayList<>();
		Collections.copy(list, res);
		System.out.println(res);
	}
}
