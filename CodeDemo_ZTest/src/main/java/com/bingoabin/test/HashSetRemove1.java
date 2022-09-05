package com.bingoabin.test;

import com.bingoabin.utils.ListUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/31 12:41
 * @Description:
 */
public class HashSetRemove1 {
	public static void main(String[] args){
		HashSet<Integer> tableSets = new HashSet<>();
		tableSets.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

		List<Integer> tables = Arrays.asList(1, 2, 3, 4);
		ListUtil.stream(tables).forEach(table -> {
			if (tableSets.contains(table)) {
				tableSets.remove(table);
			}
		});
		System.out.println(tableSets);
	}
}
