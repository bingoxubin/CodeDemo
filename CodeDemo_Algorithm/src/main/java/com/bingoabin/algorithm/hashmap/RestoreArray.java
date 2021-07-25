package com.bingoabin.algorithm.hashmap;

import java.util.*;

/**
 * @author xubin34
 * @date 2021/7/26 1:04 上午
 */
public class RestoreArray {
	//Leetcode 1743. 从相邻元素对还原数组
	//示例：输入：adjacentPairs = [[2,1],[3,4],[3,2]]  输出：[1,2,3,4]
	//分析：存在一个由 n 个不同元素组成的整数数组 nums,给一个二维数组，里面记着n个元素的相邻情况，还原出原数组的顺序
	//思路：1.用hashmap记下每个元素的左右情况，如果为边界那么value只有一个，如果不是边界value有2个
	//     2.遍历hashmap，如果value个数为1的话那么就是开头或者结尾，结果只要返回一个可能值，所以可以直接指定第一个值为value为1的值
	//     3.确认了第一个值，那么第二个值就可以确认了，那么第三个值就是  不能跟前面第二个值相同，不断排除即可
	public static void main(String[] args) {
		int[][] arr = {{2, 1}, {3, 4}, {3, 2}};
		System.out.println(Arrays.toString(restoreArray(arr)));
		System.out.println(Arrays.toString(restoreArray1(arr)));
	}

	//思路：1.用hashmap记下每个元素的左右情况，如果为边界那么value只有一个，如果不是边界value有2个
	//     2.遍历hashmap，如果value个数为1的话那么就是开头或者结尾，结果只要返回一个可能值，所以可以直接指定第一个值为value为1的值
	//     3.确认了第一个值，那么第二个值就可以确认了，那么第三个值就是  不能跟前面第二个值相同，不断排除即可
	public static int[] restoreArray(int[][] adjacentPairs) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int[] adjacentPair : adjacentPairs) {
			//方式一实现
			//putIfAbsent 跟put的区别就是put是不断更新，putIfAbsent是如果存在不插入，如果不存在插入adjacentPair[0],new ArrayList<Integer>
			map.putIfAbsent(adjacentPair[0], new ArrayList<Integer>());
			map.putIfAbsent(adjacentPair[1], new ArrayList<Integer>());
			map.get(adjacentPair[0]).add(adjacentPair[1]);
			map.get(adjacentPair[1]).add(adjacentPair[0]);
			//方式二实现
			// if(!map.containsKey(adjacentPair[0])){
			// 	map.put(adjacentPair[0],new ArrayList<>());
			// }
			// if(map.containsKey(adjacentPair[0])){
			// 	map.get(adjacentPair[0]).add(adjacentPair[1]);
			// }
			// if(!map.containsKey(adjacentPair[1])){
			// 	map.put(adjacentPair[1],new ArrayList<>());
			// }
			// if(map.containsKey(adjacentPair[1])){
			// 	map.get(adjacentPair[1]).add(adjacentPair[0]);
			// }
		}

		int n = adjacentPairs.length + 1;
		int[] ret = new int[n];
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			int e = entry.getKey();
			List<Integer> adj = entry.getValue();
			if (adj.size() == 1) {
				ret[0] = e;
				break;
			}
		}

		ret[1] = map.get(ret[0]).get(0);
		for (int i = 2; i < n; i++) {
			List<Integer> adj = map.get(ret[i - 1]);
			ret[i] = ret[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0);
		}
		return ret;
	}

	//自己实现一遍，思路一样的
	public static int[] restoreArray1(int[][] arrs) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int[] arr : arrs) {
			map.putIfAbsent(arr[0], new ArrayList<Integer>());
			map.putIfAbsent(arr[1], new ArrayList<Integer>());
			map.get(arr[0]).add(arr[1]);
			map.get(arr[1]).add(arr[0]);
		}

		int[] res = new int[arrs.length + 1];
		for (int key : map.keySet()) {
			if (map.get(key).size() == 1) {
				res[0] = key;
				break;
			}
		}

		res[1] = map.get(res[0]).get(0);
		for (int i = 2; i < res.length; i++) {
			ArrayList<Integer> list = map.get(res[i - 1]);
			res[i] = res[i - 2] == list.get(0) ? list.get(1) : list.get(0);
		}
		return res;
	}
}
