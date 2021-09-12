package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/9/13 12:45 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumberOfBoomerangs {
	//Leetcode 447. 回旋镖的数量
	//示例：输入：points = [[0,0],[1,0],[2,0]]   输出：2   解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
	//分析：平面上互不相同的点，回旋镖组合为3个点  满足第一个点到第二个点 跟第三个点的距离一致 那么就是一个回旋镖  求回旋镖的数量
	//思路：采用hashmap的思路，按照每个点求出距离  放到hashmap中，统计hashmap的value的个数  那么距离为key的  种类有 value * (value - 1)
	public static void main(String[] args) {
		NumberOfBoomerangs numberOfBoomerangs = new NumberOfBoomerangs();
		int[][] arr = {{0, 0}, {1, 0}, {2, 0}};
		System.out.println(numberOfBoomerangs.numberOfBoomerangs(arr));
	}

	public int numberOfBoomerangs(int[][] points) {
		// 记录最终结果
		int res = 0;
		//遍历所有点中的每个节点 从第一个节点开始遍历
		for (int[] point : points) {
			//定义hashmap key为与point的距离，value为等于该距离的点的个数
			HashMap<Integer, Integer> map = new HashMap<>();
			//计算后面每个点 跟当前点之间的距离 并放入到map中
			for (int[] p : points) {
				int distance = (point[0] - p[0]) * (point[0] - p[0]) + (point[1] - p[1]) * (point[1] - p[1]);
				map.put(distance, map.getOrDefault(distance, 0) + 1);
			}
			//遍历map 将value的值进行统计 求得结果
			for (Integer key : map.keySet()) {
				int value = map.get(key);
				res += value * (value - 1);
			}
		}
		return res;
	}
}
