package com.bingoabin.algorithm.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author xubin03
 * @date 2021/5/2 12:02 下午
 */
public class BrickWall {
	//Leetcode.554 砖墙  https://leetcode-cn.com/problems/brick-wall/
	//样例：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]   返回：2
	//分析：每个元素表示横向的长度，纵向的长度都是1，得出纵向的一条线穿过的最少的砖块
	//思路：先用hashmap存储，每列的空隙(出去列头和列尾，因为都是0），求出空隙最大值，用wall的长度（行数）- 最大空隙
	public static void main(String[] args) {
		// ArrayList<Integer> list = new ArrayList(Arrays.asList(1, 2, 2, 1));
		// List<Integer> list = Arrays.asList(1,2,2,1);
		List<List<Integer>> list = new ArrayList<>();
		list.add(Arrays.asList(1, 2, 2, 1));
		list.add(Arrays.asList(3, 1, 2));
		list.add(Arrays.asList(1, 3, 2));
		list.add(Arrays.asList(2, 4));
		list.add(Arrays.asList(3, 1, 2));
		list.add(Arrays.asList(1, 3, 1, 1));
		for (List<Integer> m : list) {
			for (Integer n : m) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
		System.out.println(leastBricks(list));
	}

	public static int leastBricks(List<List<Integer>> wall) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (List<Integer> list : wall) {
			int col = 0;
			//主要是想扣除最后一列
			int len = list.size();
			for (int i = 0; i < len - 1; i++) {
				col += list.get(i);
				map.put(col, map.getOrDefault(col, 0) + 1);
			}
		}
		//求出缝隙的最大值
		int max = 0;
		for (Integer key : map.keySet()) {
			max = Math.max(max, map.get(key));
		}
		return wall.size() - max;
	}
}
