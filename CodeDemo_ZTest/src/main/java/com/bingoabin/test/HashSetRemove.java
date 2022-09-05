package com.bingoabin.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author bingoabin
 * @date 2022/8/31 12:26
 * @Description: 删除hashset
 */
public class HashSetRemove {
	public static void main(String[] args){
		HashSet<Integer> set = new HashSet<>();
		set.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (next % 2 == 0) {
				iterator.remove();
			}
		}

		// for (Integer num : set) {
		// 	if (num % 2 == 0) {
		// 		set.remove(num);
		// 	}
		// }
		System.out.println(set.size());
	}
}
